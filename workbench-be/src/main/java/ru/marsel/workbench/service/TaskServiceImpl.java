package ru.marsel.workbench.service;

import java.io.File;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marsel.workbench.mapper.TaskMapper;
import ru.marsel.workbench.model.GoogleDriveData;
import ru.marsel.workbench.model.Task;
import ru.marsel.workbench.model.TaskStatus;
import ru.marsel.workbench.repository.GoogleDriveDataRepository;
import ru.marsel.workbench.repository.TaskRepository;
import ru.marsel.workbench.service.interfaces.GoogleDriveService;
import ru.marsel.workbench.service.interfaces.TaskService;
import ru.model.workbench.model.TaskDto;
import ru.model.workbench.model.TaskItemDto;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final GoogleDriveService googleDriveService;
    private final TaskRepository taskRepository;
    private final GoogleDriveDataRepository googleDriveDataRepository;
    private final TaskMapper taskMapper;

    @Transactional
    @SneakyThrows
    @Override
    public String createTemplateInDrive(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(TaskStatus.IN_PROGRESS);
        taskRepository.save(task);
        File file = new ClassPathResource(task.getType().getTemplatePath()).getFile();
        GoogleDriveData googleDriveData = googleDriveDataRepository.findByProjectId(task.getRoadmap().getProject().getId());
        return googleDriveService.uploadFileToFolder(file, googleDriveData.getProjectFolderId(), "%s | %s".formatted(task.getType().getTitle(), task.getRoadmap().getProject().getName()));
    }

    @Transactional
    @SneakyThrows
    @Override
    public Optional<Resource> getTaskTemplateResource(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(TaskStatus.IN_PROGRESS);
        taskRepository.save(task);
        File file = new ClassPathResource(task.getType().getTemplatePath()).getFile();
        return Optional.of(new FileSystemResource(file));
    }

    @Transactional
    @Override
    public TaskDto validateTaskDocument(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(TaskStatus.DONE);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto validateTaskDocument(File file, Long taskId) {
        return null;
    }

    @Override
    public TaskItemDto getById(Long taskId) {
        return taskMapper.toTaskItemDto(taskRepository.findById(taskId).orElseThrow());
    }
}
