package ru.marsel.workbench.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.marsel.workbench.model.dto.TrelloBoard;
import ru.marsel.workbench.model.dto.TrelloCard;
import ru.marsel.workbench.model.dto.TrelloList;
import ru.marsel.workbench.props.TrelloProperties;
import ru.marsel.workbench.service.interfaces.TrelloApiClient;

@Service
@RequiredArgsConstructor
public class TrelloApiClientImpl implements TrelloApiClient {

    private final TrelloProperties trelloProperties;

    private final WebClient trello;

    private final static String GET_ALL_BOARDS_URL = "/1/members/me/boards";
    private final static String CREATE_CARD = "/1/cards";
    private final static String GET_BOARD_LISTS = "/1/boards/{id}/lists";
    private final static String UPDATE_CARD = "/1/cards/{id}";


    @Override
    public Flux<TrelloBoard> getAllBoards(String token) {
        return trello
            .get()
            .uri(uriBuilder -> uriBuilder
                .path(GET_ALL_BOARDS_URL)
                .queryParam("key", trelloProperties.getKey())
                .queryParam("token", token)
                .build())
            .retrieve()
            .bodyToFlux(new ParameterizedTypeReference<>() {});
    }

    @Override
    public Mono<TrelloCard> createCard(String token, String listId, String name, String desc) {
        return trello.post()
            .uri(uriBuilder -> uriBuilder
                .path(CREATE_CARD)
                .queryParam("key", trelloProperties.getKey())
                .queryParam("token", token)
                .queryParam("name", name)
                .queryParam("desc", desc)
                .queryParam("idList", listId)
                .build())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    @Override
    public Flux<TrelloList> getBoardLists(String token, String boardId) {
        return trello.get()
            .uri(uriBuilder -> uriBuilder
                .path(GET_BOARD_LISTS)
                .queryParam("key", trelloProperties.getKey())
                .queryParam("token", token)
                .build(boardId))
            .retrieve()
            .bodyToFlux(new ParameterizedTypeReference<>() {});
    }

    @Override
    public Mono<TrelloCard> updateCard(String token, String cardId, String name, String desc, String idList) {
        return trello.put()
            .uri(uriBuilder -> {
                var path = uriBuilder.path(UPDATE_CARD)
                    .queryParam("key", trelloProperties.getKey())
                    .queryParam("token", token);
                if (name != null) {
                    path.queryParam("name", name);
                }
                if (desc != null) {
                    path.queryParam("desc", desc);
                }
                if (idList != null) {
                    path.queryParam("idList", idList);
                }
                return path.build(cardId);
                })
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
