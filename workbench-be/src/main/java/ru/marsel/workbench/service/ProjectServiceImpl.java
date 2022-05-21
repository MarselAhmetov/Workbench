package ru.marsel.workbench.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marsel.workbench.mapper.ProjectMapper;
import ru.marsel.workbench.repository.ProjectAttributeRepository;
import ru.marsel.workbench.repository.ProjectRepository;
import ru.marsel.workbench.security.AuthContext;
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

    @Override
    public ProjectDto createProject(ProjectCreationRequestDto dto) {
        var project = projectMapper.toEntity(dto, authContext.getUser());
        var attributes = projectAttributeRepository.findAllById(dto.getAttributesIds());
        project.setRoadmap(roadmapService.getDefaultRoadmap(attributes));
        return projectMapper.toDto(projectRepository.save(project));
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
}
