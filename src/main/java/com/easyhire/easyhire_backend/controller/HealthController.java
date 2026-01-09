package com.easyhire.easyhire_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
        @GetMapping("/health")
        public String healthCheck() {
            return "EasyHire Backend is UP";
        }
    }


