package ru.marsel.workbench.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.marsel.workbench.service.interfaces.GoogleDriveService;
import ru.model.workbench.api.IntegrationApi;
import ru.model.workbench.model.GoogleDriveCodeDto;

@RestController
@RequiredArgsConstructor
public class IntegrationController implements IntegrationApi {

    private final GoogleDriveService googleDriveService;

    @Override
    public ResponseEntity<Void> getAccessGoogleDriveApi(GoogleDriveCodeDto googleDriveCodeDto) {
        googleDriveService.exchangeCodeAndSaveCredentials(googleDriveCodeDto.getCode());
        return ResponseEntity.ok().build();
    }
}
