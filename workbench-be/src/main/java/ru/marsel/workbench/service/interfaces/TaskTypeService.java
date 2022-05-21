package ru.marsel.workbench.service.interfaces;

import java.io.File;
import java.util.List;
import ru.marsel.workbench.model.TaskType;

public interface TaskTypeService {
    List<TaskType> getAll();

    TaskType save(TaskType taskType);

    File getDescriptionFile(String filePath);
}
