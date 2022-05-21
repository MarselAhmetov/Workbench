package ru.marsel.workbench.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marsel.workbench.mapper.ProjectAttributeMapper;
import ru.marsel.workbench.model.ProjectAttribute;
import ru.marsel.workbench.repository.ProjectAttributeRepository;
import ru.marsel.workbench.service.interfaces.ProjectAttributeService;
import ru.model.workbench.model.ProjectAttributeDto;

@Service
@RequiredArgsConstructor
public class ProjectAttributeServiceImpl implements ProjectAttributeService {

    private final ProjectAttributeRepository projectAttributeRepository;
    private final ProjectAttributeMapper projectAttributeMapper;

    @Override
    public List<ProjectAttributeDto> getAllProjectAttributes() {
        return projectAttributeRepository.findAll().stream()
            .map(projectAttributeMapper::toDto)
            .toList();
    }
}
