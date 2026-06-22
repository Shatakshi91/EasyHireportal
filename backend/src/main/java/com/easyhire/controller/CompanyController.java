package com.easyhire.controller;

import com.easyhire.dto.CompanyRequest;
import com.easyhire.dto.CompanyResponse;
import com.easyhire.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/companies")
@Tag(name = "Companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/me")
    public ResponseEntity<CompanyResponse> getMyCompany(Authentication authentication) {
        UUID recruiterId = (UUID) authentication.getPrincipal();

        return companyService.getMyCompany(recruiterId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(
            @Valid @RequestBody CompanyRequest request,
            Authentication authentication) {

        UUID recruiterId = (UUID) authentication.getPrincipal();

        return ResponseEntity.status(201).body(companyService.create(recruiterId, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody CompanyRequest request,
            Authentication authentication) {

        UUID recruiterId = (UUID) authentication.getPrincipal();

        return ResponseEntity.ok(companyService.update(recruiterId, id, request));
    }
}
