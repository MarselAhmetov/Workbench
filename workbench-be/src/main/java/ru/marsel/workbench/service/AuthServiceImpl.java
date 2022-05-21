package ru.marsel.workbench.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.marsel.workbench.mapper.UserMapper;
import ru.marsel.workbench.model.user.Role;
import ru.marsel.workbench.model.user.User;
import ru.marsel.workbench.repository.UserRepository;
import ru.marsel.workbench.security.jwt.JwtTokenProvider;
import ru.marsel.workbench.service.interfaces.AuthService;
import ru.model.workbench.model.SignInRequestDto;
import ru.model.workbench.model.SignInResponseDto;
import ru.model.workbench.model.SignUpRequestDto;
import ru.model.workbench.model.SignUpResponseDto;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtProvider;
    private final UserMapper userMapper;

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        User user = User.builder()
            .email(requestDto.getEmail())
            .password(passwordEncoder.encode(requestDto.getPassword()))
            .role(Role.USER)
            .build();
        user = userRepository.save(user);
        String token = jwtProvider.createToken(user.getEmail());
        return new SignUpResponseDto()
            .user(userMapper.toUserDto(user))
            .token(token);
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        User user = userRepository.findUserByEmail(signInRequestDto.getEmail())
            .orElseThrow(IllegalArgumentException::new);
        if (passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            String token = jwtProvider.createToken(user.getEmail());
            return new SignInResponseDto()
                .token(token)
                .user(userMapper.toUserDto(user));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
