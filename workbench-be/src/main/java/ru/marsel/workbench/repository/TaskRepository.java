package ru.marsel.workbench.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.marsel.workbench.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select * from Task as t left join task_parents tp on t.id = tp.task_id where tp.parents_id = :taskId", nativeQuery = true)
    List<Task> getChildren(Long taskId);
}
