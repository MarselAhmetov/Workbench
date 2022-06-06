package ru.marsel.workbench;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class WorkbenchApplication {

    @Value("${google.client-credentials.file-name}")
    private String CLIENT_SECRET_FILE_NAME;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SneakyThrows
    public GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow() {
        var CREDENTIALS_FOLDER = new java.io.File(System.getProperty("user.home"), "credentials");
        var SCOPES = Collections.singletonList(DriveScopes.DRIVE);
        var clientSecretFilePath = new java.io.File(CREDENTIALS_FOLDER, CLIENT_SECRET_FILE_NAME);
        var jsonFactory = new GsonFactory();

        if (!clientSecretFilePath.exists()) {
            throw new FileNotFoundException("Please copy " + CLIENT_SECRET_FILE_NAME + " to folder: " + CREDENTIALS_FOLDER.getAbsolutePath());
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
            jsonFactory,
            new InputStreamReader(new FileInputStream(clientSecretFilePath))
        );

        return new GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            jsonFactory,
            clientSecrets,
            SCOPES)
            .setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER))
            .setAccessType("offline")
            .build();
    }

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(WorkbenchApplication.class, args);
    }

    @Bean
    protected CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.addAllowedHeader("*");

        Stream.of("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
            .forEach(config::addAllowedMethod);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
