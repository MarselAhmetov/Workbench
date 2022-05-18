package ru.marsel.workbench.mapper;

import org.mapstruct.Mapper;
import ru.marsel.workbench.model.TaskType;
import ru.model.workbench.model.TaskTypeDto;

@Mapper(componentModel = "spring")
public interface NodeTypeMapper {
    TaskTypeDto toDto(TaskType taskType);
}
