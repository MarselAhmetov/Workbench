package ru.marsel.workbench.service;

import java.util.List;
import ru.model.workbench.model.ProjectCreationRequestDto;
import ru.model.workbench.model.ProjectDto;

public interface ProjectService {
    ProjectDto createProject(ProjectCreationRequestDto dto);
    List<ProjectDto> findAllByUser();
    ProjectDto getProject(Long id);
}
