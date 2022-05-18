package ru.marsel.workbench.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUserId(Long userId);
}
