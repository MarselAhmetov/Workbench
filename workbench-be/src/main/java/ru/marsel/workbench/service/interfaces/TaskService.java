package ru.marsel.workbench.service.interfaces;

import java.util.Optional;
import org.springframework.core.io.Resource;

public interface TaskService {
    String createTemplateInDrive(Long taskId);
    Optional<Resource> getTaskTemplateResource(Long taskId);
}
