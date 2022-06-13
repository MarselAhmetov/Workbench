package ru.marsel.workbench.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.ProjectTrelloSettings;

public interface ProjectTrelloSettingsRepository extends JpaRepository<ProjectTrelloSettings, Long> {
    Optional<ProjectTrelloSettings> findByProjectId(Long projectId);
}
