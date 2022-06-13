package ru.marsel.workbench.service.interfaces;

import ru.marsel.workbench.model.Project;
import ru.marsel.workbench.model.Task;
import ru.marsel.workbench.model.dto.TrelloCard;

public interface TrelloService {

    void saveToken(String token);
    TrelloCard createTask(Task task);

    TrelloCard moveTaskToDone(Task task);

    void createTrelloSettings(Project project);
}
