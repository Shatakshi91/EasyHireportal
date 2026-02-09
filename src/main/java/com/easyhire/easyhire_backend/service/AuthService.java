package com.easyhire.easyhire_backend.service;

import com.easyhire.easyhire_backend.dto.LoginRequest;
import com.easyhire.easyhire_backend.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    LoginResponse register(com.easyhire.easyhire_backend.dto.RegisterRequest request);
}