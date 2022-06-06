package ru.marsel.workbench.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import java.nio.file.Files;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.marsel.workbench.model.user.User;
import ru.marsel.workbench.repository.UserRepository;
import ru.marsel.workbench.security.AuthContext;
import ru.marsel.workbench.service.interfaces.GoogleDriveService;

@Service
@RequiredArgsConstructor
public class GoogleDriveServiceImpl implements GoogleDriveService {

    private final AuthContext authContext;
    private final GoogleAuthorizationCodeFlow googleFlow;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public String createFolder(String name) {
        User user = authContext.getUser();
        Credential credential = googleFlow.loadCredential(user.getId().toString());
        var drive = getDrive(credential);
        File fileMetadata = new File();
        fileMetadata.setName(name);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        File file = drive.files().create(fileMetadata)
            .setFields("id")
            .execute();
        return file.getId();
    }

    @SneakyThrows
    @Override
    public String uploadFileToFolder(java.io.File file, String folderId, String filename) {
        User user = authContext.getUser();
        Credential credential = googleFlow.loadCredential(user.getId().toString());
        var drive = getDrive(credential);
        File fileMetadata = new File();
        fileMetadata.setName(filename);
        if (folderId != null) {
            fileMetadata.setParents(Collections.singletonList(folderId));
        }
        String mimeType = Files.probeContentType(file.toPath());
        FileContent mediaContent = new FileContent(mimeType, file);
        File driveFile = drive.files().create(fileMetadata, mediaContent)
            .setFields("id, parents")
            .execute();
        return driveFile.getId();
    }

    @SneakyThrows
    @Override
    public void exchangeCodeAndSaveCredentials(String code) {
        User user = authContext.getUser();
        TokenResponse tokenResponse = googleFlow.newTokenRequest(code)
            .setRedirectUri("http://localhost:4200/login/oauth2/code/google")
            .execute();
        googleFlow.createAndStoreCredential(tokenResponse, user.getId().toString());
        user.setIsGoogleConnected(true);
        userRepository.save(user);
    }

    @SneakyThrows
    private Drive getDrive(Credential credential) {
        return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory(), credential)
            .setApplicationName("Workbench")
            .build();
    }
}
