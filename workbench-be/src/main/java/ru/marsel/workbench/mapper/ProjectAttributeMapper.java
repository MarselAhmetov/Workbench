package ru.marsel.workbench.mapper;

import org.mapstruct.Mapper;
import ru.marsel.workbench.model.ProjectAttribute;
import ru.model.workbench.model.ProjectAttributeDto;

@Mapper(componentModel = "spring")
public interface ProjectAttributeMapper {
    ProjectAttributeDto toDto(ProjectAttribute entity);
}
