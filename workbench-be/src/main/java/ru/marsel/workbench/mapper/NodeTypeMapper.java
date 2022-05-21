package ru.marsel.workbench.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.marsel.workbench.model.LongIdBaseEntity;
import ru.marsel.workbench.model.TaskType;
import ru.model.workbench.model.TaskTypeDto;

@Mapper(componentModel = "spring")
public interface NodeTypeMapper {
    @Mapping(target = "parentsIds", expression = "java(toParentIds(taskType))")
    TaskTypeDto toDto(TaskType taskType);

    default List<Long> toParentIds(TaskType taskType) {
        return taskType.getParents().stream().map(LongIdBaseEntity::getId).toList();
    }
}
