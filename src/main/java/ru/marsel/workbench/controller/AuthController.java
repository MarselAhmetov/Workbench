package ru.marsel.workbench.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.model.workbench.api.AuthApi;
import ru.model.workbench.model.SignInRequestDto;
import ru.model.workbench.model.SignInResponseDto;
import ru.model.workbench.model.SignUpRequestDto;
import ru.model.workbench.model.SignUpResponseDto;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    @Override
    public ResponseEntity<SignInResponseDto> signIn(SignInRequestDto signInRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<SignUpResponseDto> signUp(SignUpRequestDto signUpRequestDto) {
        return null;
    }
}
