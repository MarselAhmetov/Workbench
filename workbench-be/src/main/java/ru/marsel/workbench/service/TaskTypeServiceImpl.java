package ru.marsel.workbench.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marsel.workbench.model.TaskType;
import ru.marsel.workbench.repository.TaskTypeRepository;
import ru.marsel.workbench.service.interfaces.TaskTypeService;

@Service
@RequiredArgsConstructor
public class TaskTypeServiceImpl implements TaskTypeService {
    private final TaskTypeRepository nodeRepository;

    public List<TaskType> getAll() {
        return nodeRepository.findAll();
    }

    public TaskType save(TaskType taskType) {
        return nodeRepository.save(taskType);
    }
}
