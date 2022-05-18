package ru.marsel.workbench.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
