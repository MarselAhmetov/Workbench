package ru.marsel.workbench.service.interfaces;

public interface GoogleDriveService {
    String createFolder(String name);
    String uploadFileToFolder(java.io.File file, String folderId, String filename);
    void exchangeCodeAndSaveCredentials(String code);
}
