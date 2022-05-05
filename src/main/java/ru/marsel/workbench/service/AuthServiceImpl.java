package ru.marsel.workbench.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.marsel.workbench.mapper.UserMapper;
import ru.marsel.workbench.model.User;
import ru.marsel.workbench.repository.UserRepository;
import ru.marsel.workbench.security.JwtProvider;
import ru.model.workbench.model.SignInRequestDto;
import ru.model.workbench.model.SignInResponseDto;
import ru.model.workbench.model.SignUpRequestDto;
import ru.model.workbench.model.SignUpResponseDto;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        User user = User.builder()
            .email(requestDto.getEmail())
            .password(passwordEncoder.encode(requestDto.getPassword()))
            .build();
        userRepository.save(user);
        String token = jwtProvider.generateToken(user);
        return new SignUpResponseDto()
            .user(userMapper.toUserDto(user))
            .token(token);
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        User user = userRepository.findUserByEmail(signInRequestDto.getEmail())
            .orElseThrow(IllegalArgumentException::new);
        if (passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            String token = jwtProvider.generateToken(user);
            return new SignInResponseDto()
                .token(token)
                .user(userMapper.toUserDto(user));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
