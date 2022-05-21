package ru.marsel.workbench.service.interfaces;

import java.util.List;
import ru.marsel.workbench.model.ProjectAttribute;
import ru.model.workbench.model.ProjectAttributeDto;

public interface ProjectAttributeService {
    List<ProjectAttributeDto> getAllProjectAttributes();
}
