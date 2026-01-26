package com.easyhire.easyhire_backend.controller;

import com.easyhire.easyhire_backend.dto.LoginRequest;
import com.easyhire.easyhire_backend.dto.LoginResponse;
import com.easyhire.easyhire_backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
