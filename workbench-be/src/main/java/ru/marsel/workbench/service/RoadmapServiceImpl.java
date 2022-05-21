package ru.marsel.workbench.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marsel.workbench.model.ProjectAttribute;
import ru.marsel.workbench.model.Roadmap;
import ru.marsel.workbench.model.Task;
import ru.marsel.workbench.model.TaskType;
import ru.marsel.workbench.repository.TaskTypeRepository;
import ru.marsel.workbench.repository.RoadmapRepository;
import ru.marsel.workbench.repository.TaskRepository;
import ru.marsel.workbench.service.interfaces.RoadmapService;

@Service
@RequiredArgsConstructor
public class RoadmapServiceImpl implements RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final TaskRepository taskRepository;
    private final TaskTypeRepository taskTypeRepository;

    @Override
    @Transactional
    public Roadmap createRoadmap(String title, List<Long> nodeIds) {
        var taskTypes = taskTypeRepository.findAllById(nodeIds);
        var tasks = taskTypes.stream()
            .map(nodeType -> Task.builder()
                .type(nodeType)
                .build())
            .map(taskRepository::save)
            .toList();
        linkTasks(tasks);
        var roadmap = Roadmap.builder()
            .tasks(tasks)
            .title(title)
            .build();
        return roadmapRepository.save(roadmap);
    }

    @Override
    public Roadmap getRoadmap(Long id) {
        return roadmapRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Roadmap not found"));
    }

    @Override
    public void deleteRoadmap(Long id) {
        roadmapRepository.deleteById(id);
    }

    @Override
    public List<Roadmap> getAllRoadmaps() {
        return roadmapRepository.findAll();
    }

    @Override
    public Roadmap getDefaultRoadmap(List<ProjectAttribute> attributes) {
        var taskTypes = attributes.stream()
            .flatMap(attribute -> attribute.getTaskTypes().stream())
            .distinct()
            .toList();
        var tasks = taskTypes.stream()
            .map(Task::new)
            .map(taskRepository::save)
            .toList();
        tasks = linkTasks(tasks);
        var roadmap = Roadmap.builder()
            .title("Default")
            .tasks(tasks)
            .build();
        return roadmapRepository.save(roadmap);
    }

    private List<Task> linkTasks(List<Task> tasks) {
        tasks.forEach(task -> {
            var parents = findParentTasks(task, tasks);
            task.setParents(parents);
        });
        return tasks;
    }

    private List<Task> findParentTasks(Task task, List<Task> allTasks) {
        List<Task> result = new ArrayList<>();
        List<TaskType> resultTypes = new ArrayList<>();
        List<TaskType> parentsTemp = new ArrayList<>(task.getType().getParents());
        var allTaskTypes = allTasks.stream().map(Task::getType).toList();
        while (resultTypes.isEmpty()) {
            resultTypes = parentsTemp.stream()
                .distinct()
                .filter(allTaskTypes::contains)
                .toList();
            parentsTemp = parentsTemp.stream()
                .flatMap(parent -> parent.getParents().stream())
                .distinct()
                .toList();
            if (parentsTemp.isEmpty()) {
                break;
            }
        }
        for (Task allTask : allTasks) {
            if (resultTypes.contains(allTask.getType())) {
                result.add(allTask);
            }
        }
        return result;
    }


}
