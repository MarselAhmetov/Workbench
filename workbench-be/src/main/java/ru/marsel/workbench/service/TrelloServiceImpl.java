package ru.marsel.workbench.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marsel.workbench.model.Project;
import ru.marsel.workbench.model.ProjectTrelloSettings;
import ru.marsel.workbench.model.Task;
import ru.marsel.workbench.model.dto.TrelloCard;
import ru.marsel.workbench.model.user.User;
import ru.marsel.workbench.repository.ProjectTrelloSettingsRepository;
import ru.marsel.workbench.repository.TaskRepository;
import ru.marsel.workbench.repository.UserRepository;
import ru.marsel.workbench.security.AuthContext;
import ru.marsel.workbench.service.interfaces.TrelloApiClient;
import ru.marsel.workbench.service.interfaces.TrelloService;

@Service
@RequiredArgsConstructor
public class TrelloServiceImpl implements TrelloService {

    private final AuthContext authContext;
    private final UserRepository userRepository;
    private final TrelloApiClient trelloApiClient;
    private final ProjectTrelloSettingsRepository projectTrelloSettingsRepository;
    private final TaskRepository taskRepository;

    @Override
    public void saveToken(String token) {
        User user = authContext.getUser();
        user.setTrelloToken(token);
        userRepository.save(user);
    }

    @Override
    public TrelloCard createTask(Task task) {
        var settings = projectTrelloSettingsRepository.findByProjectId(task.getRoadmap().getProject().getId()).orElseThrow();
        User user = authContext.getUser();
        if (user.getTrelloToken() != null) {
            var card =  trelloApiClient.createCard(user.getTrelloToken(), settings.getTodoIdList(), task.getType().getTitle(), task.getType().getDescription()).block();
            if (card != null) {
                task.setTrelloCardId(card.getId());
                taskRepository.save(task);
            }
        }
        return null;
    }

    @Override
    public TrelloCard moveTaskToDone(Task task) {
        var settings = projectTrelloSettingsRepository.findByProjectId(task.getRoadmap().getProject().getId()).orElseThrow();
        User user = authContext.getUser();
        if (user.getTrelloToken() != null && task.getTrelloCardId() != null) {
            return trelloApiClient.updateCard(user.getTrelloToken(), task.getTrelloCardId(), null, null, settings.getDoneIdList()).block();
        }
        return null;
    }

    @Override
    public void createTrelloSettings(Project project) {
        User user = authContext.getUser();
        if (user.getTrelloToken() != null) {
            // TODO
            ProjectTrelloSettings projectTrelloSettings = ProjectTrelloSettings.builder()
                .project(project)
                .boardId("62a738d9666fd082998f5a03")
                .todoIdList("62a738e8200e783b341abb5b")
                .doneIdList("62a738fe1cdca365ef8a13ed")
                .build();
            projectTrelloSettingsRepository.save(projectTrelloSettings);
        }
    }
}
