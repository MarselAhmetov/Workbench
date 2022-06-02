package ru.marsel.workbench.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marsel.workbench.mapper.ProjectMapper;
import ru.marsel.workbench.model.GoogleDriveData;
import ru.marsel.workbench.model.Project;
import ru.marsel.workbench.model.user.User;
import ru.marsel.workbench.repository.GoogleDriveDataRepository;
import ru.marsel.workbench.repository.ProjectAttributeRepository;
import ru.marsel.workbench.repository.ProjectRepository;
import ru.marsel.workbench.security.AuthContext;
import ru.marsel.workbench.service.interfaces.GoogleDriveService;
import ru.marsel.workbench.service.interfaces.ProjectService;
import ru.marsel.workbench.service.interfaces.RoadmapService;
import ru.model.workbench.model.ProjectCreationRequestDto;
import ru.model.workbench.model.ProjectDto;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final AuthContext authContext;
    private final RoadmapService roadmapService;
    private final ProjectAttributeRepository projectAttributeRepository;
    private final GoogleDriveService googleDriveService;
    private final GoogleDriveDataRepository googleDriveDataRepository;

    @Override
    @Transactional
    public ProjectDto createProject(ProjectCreationRequestDto dto) {
        User user = authContext.getUser();
        var project = projectMapper.toEntity(dto, user);
        var attributes = projectAttributeRepository.findAllById(dto.getAttributesIds());
        project.setAttributes(attributes);
        project.setRoadmap(roadmapService.getDefaultRoadmap(attributes));
        project = projectRepository.save(project);
        if (user.getIsGoogleConnected()) {
            createGoogleDriveFolder(project);
        }
        return projectMapper.toDto(project);
    }

    @Override
    public List<ProjectDto> findAllByUser() {
        return projectRepository.findAllByUserId(authContext.getUser().getId()).stream()
            .map(projectMapper::toDto)
            .toList();
    }

    @Override
    public ProjectDto getProject(Long id) {
        return projectMapper.toDto(projectRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Project not found")));
    }

    private void createGoogleDriveFolder(Project project) {
        var folderId = googleDriveService.createFolder(project.getName());
        GoogleDriveData driveData = GoogleDriveData.builder()
            .projectFolderId(folderId)
            .project(project)
            .build();
        googleDriveDataRepository.save(driveData);
    }
}
