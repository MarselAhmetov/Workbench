package ru.marsel.workbench.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.marsel.workbench.service.interfaces.AuthService;
import ru.model.workbench.api.AuthApi;
import ru.model.workbench.model.SignInRequestDto;
import ru.model.workbench.model.SignInResponseDto;
import ru.model.workbench.model.SignUpRequestDto;
import ru.model.workbench.model.SignUpResponseDto;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<SignInResponseDto> signIn(SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok(authService.signIn(signInRequestDto));
    }

    @Override
    public ResponseEntity<SignUpResponseDto> signUp(SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(authService.signUp(signUpRequestDto));
    }
}
