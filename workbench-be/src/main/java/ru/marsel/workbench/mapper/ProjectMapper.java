package ru.marsel.workbench.mapper;

import org.mapstruct.Mapper;
import ru.marsel.workbench.model.Project;
import ru.marsel.workbench.model.Roadmap;
import ru.marsel.workbench.model.user.User;
import ru.model.workbench.model.ProjectCreationRequestDto;
import ru.model.workbench.model.ProjectDto;
import ru.model.workbench.model.RoadmapDto;

@Mapper(componentModel = "spring", uses = { TaskMapper.class })
public interface ProjectMapper {
    ProjectDto toDto(Project entity);
    RoadmapDto toDto(Roadmap entity);
    Project toEntity(ProjectCreationRequestDto dto);
    default Project toEntity(ProjectCreationRequestDto dto, User user) {
        var project = toEntity(dto);
        project.setUser(user);
        return project;
    }
}
