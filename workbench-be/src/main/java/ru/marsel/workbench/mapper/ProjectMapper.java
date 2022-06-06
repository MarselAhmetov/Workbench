package ru.marsel.workbench.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.marsel.workbench.model.Project;
import ru.marsel.workbench.model.Roadmap;
import ru.marsel.workbench.model.Task;
import ru.marsel.workbench.model.TaskStatus;
import ru.marsel.workbench.model.user.User;
import ru.model.workbench.model.ProjectCreationRequestDto;
import ru.model.workbench.model.ProjectDto;
import ru.model.workbench.model.RoadmapDto;

@Mapper(componentModel = "spring", uses = { TaskMapper.class })
public interface ProjectMapper {
    @Mapping(target = "progress", expression = "java(getProgress(project))")
    ProjectDto toDto(Project project);
    RoadmapDto toDto(Roadmap entity);
    Project toEntity(ProjectCreationRequestDto dto);
    default Project toEntity(ProjectCreationRequestDto dto, User user) {
        var project = toEntity(dto);
        project.setUser(user);
        return project;
    }

    default Integer getProgress(Project project) {
        List<Task> tasks = project.getRoadmap().getTasks();
        var total = tasks.size();
        var done = tasks.stream().filter(task -> task.getStatus().equals(TaskStatus.DONE)).count();
        return (int) done * 100 / total;
    }
}
