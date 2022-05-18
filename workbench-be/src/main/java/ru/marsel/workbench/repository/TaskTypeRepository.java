package ru.marsel.workbench.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
}
