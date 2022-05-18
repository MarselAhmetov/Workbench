package ru.marsel.workbench.mapper;

import org.mapstruct.Mapper;
import ru.marsel.workbench.model.Project;
import ru.marsel.workbench.model.User;
import ru.model.workbench.model.ProjectCreationRequestDto;
import ru.model.workbench.model.ProjectDto;
import ru.model.workbench.model.UserDto;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto toDto(Project entity);
    Project toEntity(ProjectCreationRequestDto dto);
    default Project toEntity(ProjectCreationRequestDto dto, User user) {
        var project = toEntity(dto);
        project.setUser(user);
        return project;
    }
}
