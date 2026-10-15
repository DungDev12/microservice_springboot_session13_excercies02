package com.exam.identityservice.service;

import com.exam.identityservice.dto.request.RegisterUserRequest;
import com.exam.identityservice.dto.response.AuthRegisterResponse;
import com.exam.identityservice.entity.User;
import com.exam.identityservice.repository.UserRepository;
import com.exam.identityservice.security.jwt.JwtProvider;
import com.exam.identityservice.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public AuthRegisterResponse register(RegisterUserRequest request) {
        User user = User.builder()
                .username(request.username())
                .passwordHash(passwordEncoder.encode(request.password()))
                .role(request.role() != null ? request.role() : "USER")
                .build();
        userRepository.save(user);

        return new AuthRegisterResponse(user.getUsername(), user.getRole());
    }

    public String testToken(String username){
        User user = userRepository.findByUsername((username)).orElseThrow(
                () -> new RuntimeException("Username not found!")
        );

        return jwtProvider.generateToken(user);
    }
}
