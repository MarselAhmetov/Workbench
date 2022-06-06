package ru.marsel.workbench.service.interfaces;

import java.io.File;
import java.util.Optional;
import org.springframework.core.io.Resource;
import ru.model.workbench.model.TaskDto;
import ru.model.workbench.model.TaskItemDto;

public interface TaskService {
    String createTemplateInDrive(Long taskId);
    Optional<Resource> getTaskTemplateResource(Long taskId);
    TaskDto validateTaskDocument(Long taskId);
    TaskDto validateTaskDocument(File file, Long taskId);
    TaskItemDto getById(Long taskId);
}
