package ru.marsel.workbench.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.marsel.workbench.service.interfaces.TaskService;
import ru.model.workbench.api.TaskApi;
import ru.model.workbench.model.TaskDto;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class TaskController implements TaskApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<Resource> downloadTaskTemplate(Long taskId) {
        return taskService.getTaskTemplateResource(taskId)
            .map(resource -> ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + resource.getFilename())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource))
            .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @Override
    public ResponseEntity<Void> uploadTemplateToDrive(TaskDto taskDto) {
        taskService.createTemplateInDrive(taskDto.getId());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> validateTaskDocument(MultipartFile file, Long taskId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> validateTaskDocumentFromDrive(TaskDto taskDto) {
        return null;
    }
}
