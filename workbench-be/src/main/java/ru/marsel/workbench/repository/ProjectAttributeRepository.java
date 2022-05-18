package ru.marsel.workbench.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.ProjectAttribute;

public interface ProjectAttributeRepository extends JpaRepository<ProjectAttribute, Long> {
}
