package ru.marsel.workbench.service;

import java.io.File;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.marsel.workbench.model.GoogleDriveData;
import ru.marsel.workbench.model.Task;
import ru.marsel.workbench.repository.GoogleDriveDataRepository;
import ru.marsel.workbench.repository.TaskRepository;
import ru.marsel.workbench.service.interfaces.GoogleDriveService;
import ru.marsel.workbench.service.interfaces.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final GoogleDriveService googleDriveService;
    private final TaskRepository taskRepository;
    private final GoogleDriveDataRepository googleDriveDataRepository;

    @SneakyThrows
    @Override
    public String createTemplateInDrive(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        File file = new ClassPathResource(task.getType().getTemplatePath()).getFile();
        GoogleDriveData googleDriveData = googleDriveDataRepository.findByProjectId(task.getRoadmap().getProject().getId());
        return googleDriveService.uploadFileToFolder(file, googleDriveData.getProjectFolderId(), "%s | %s".formatted(task.getType().getTitle(), task.getRoadmap().getProject().getName()));
    }

    @SneakyThrows
    @Override
    public Optional<Resource> getTaskTemplateResource(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        File file = new ClassPathResource(task.getType().getTemplatePath()).getFile();
        return Optional.of(new FileSystemResource(file));
    }
}
