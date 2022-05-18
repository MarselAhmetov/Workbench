package ru.marsel.workbench.service;

import java.util.List;
import ru.marsel.workbench.model.ProjectAttribute;
import ru.marsel.workbench.model.Roadmap;

public interface RoadmapService {
    Roadmap createRoadmap(String title, List<Long> nodeIds);
    Roadmap getRoadmap(Long id);
    void deleteRoadmap(Long id);
    List<Roadmap> getAllRoadmaps();
    Roadmap getDefaultRoadmap(List<ProjectAttribute> attributes);
}
