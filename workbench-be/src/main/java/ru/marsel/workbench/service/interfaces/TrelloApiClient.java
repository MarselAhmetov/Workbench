package ru.marsel.workbench.service.interfaces;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.marsel.workbench.model.dto.TrelloCard;
import ru.marsel.workbench.model.dto.TrelloBoard;
import ru.marsel.workbench.model.dto.TrelloList;

public interface TrelloApiClient {
    Flux<TrelloBoard> getAllBoards(String token);
    Mono<TrelloCard> createCard(String token, String listId, String name, String desc);

    Flux<TrelloList> getBoardLists(String token, String boardId);

    Mono<TrelloCard> updateCard(String token, String cardId, String name, String desc, String idList);
}
