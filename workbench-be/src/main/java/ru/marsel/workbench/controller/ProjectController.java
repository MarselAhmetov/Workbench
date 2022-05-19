package ru.marsel.workbench.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.marsel.workbench.service.ProjectService;
import ru.model.workbench.api.ProjectApi;
import ru.model.workbench.model.ProjectCreationRequestDto;
import ru.model.workbench.model.ProjectDto;

@RestController
@RequiredArgsConstructor
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;

    @Override
    public ResponseEntity<ProjectDto> createProject(
        ProjectCreationRequestDto projectCreationRequestDto) {
        return ResponseEntity.ok(projectService.createProject(projectCreationRequestDto));
    }

    @Override
    public ResponseEntity<List<ProjectDto>> getAllProjectsByUserId() {
        return ResponseEntity.ok(projectService.findAllByUser());
    }
}