package ru.marsel.workbench.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.marsel.workbench.model.LongIdBaseEntity;
import ru.marsel.workbench.model.Task;
import ru.marsel.workbench.model.TaskType;
import ru.model.workbench.model.TaskDto;
import ru.model.workbench.model.TaskItemDto;
import ru.model.workbench.model.TaskTypeDto;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "parentsIds", expression = "java(toParentIds(taskType))")
    TaskTypeDto toDto(TaskType taskType);

    @Mapping(target = "parentsIds", expression = "java(toParentIds(task))")
    TaskDto toDto(Task task);

    @Mapping(target = "title", source = "task.type.title")
    @Mapping(target = "description", source = "task.type.description")
    @Mapping(target = "parentsIds", expression = "java(toParentIds(task))")
    TaskItemDto toTaskItemDto(Task task);

    default List<Long> toParentIds(TaskType taskType) {
        return taskType.getParents().stream().map(LongIdBaseEntity::getId).toList();
    }

    default List<Long> toParentIds(Task task) {
        return task.getParents().stream().map(LongIdBaseEntity::getId).toList();
    }
}
