package com.easyhire.easyhire_backend.service.impl;

import com.easyhire.easyhire_backend.dto.LoginRequest;
import com.easyhire.easyhire_backend.dto.LoginResponse;
import com.easyhire.easyhire_backend.entity.User;
import com.easyhire.easyhire_backend.repository.UserRepository;
import com.easyhire.easyhire_backend.security.JwtUtil;
import com.easyhire.easyhire_backend.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getTenantId(),
                user.getRole().name()
        );

        return new LoginResponse(token);
    }
}