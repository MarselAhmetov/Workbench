package ru.marsel.workbench.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.marsel.workbench.service.interfaces.AuthService;
import ru.model.workbench.api.AuthApi;
import ru.model.workbench.model.SignInRequestDto;
import ru.model.workbench.model.SignInResponseDto;
import ru.model.workbench.model.SignUpRequestDto;
import ru.model.workbench.model.SignUpResponseDto;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
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

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/login/oauth2/code/google",
        produces = { "application/json" }
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<Void> auth(@RequestParam String code) {
        System.out.println(code);
        return ResponseEntity.ok().build();
    }
}
