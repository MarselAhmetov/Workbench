package ru.marsel.workbench.service;

import ru.model.workbench.model.SignInRequestDto;
import ru.model.workbench.model.SignInResponseDto;
import ru.model.workbench.model.SignUpRequestDto;
import ru.model.workbench.model.SignUpResponseDto;

public interface AuthService {
    SignUpResponseDto signUp(SignUpRequestDto requestDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);
}
