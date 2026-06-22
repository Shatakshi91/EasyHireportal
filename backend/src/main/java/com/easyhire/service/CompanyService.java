package com.easyhire.service;

import com.easyhire.dto.CompanyRequest;
import com.easyhire.dto.CompanyResponse;
import com.easyhire.entity.Company;
import com.easyhire.entity.Role;
import com.easyhire.entity.User;
import com.easyhire.exception.BadRequestException;
import com.easyhire.exception.ResourceNotFoundException;
import com.easyhire.repository.CompanyRepository;
import com.easyhire.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Optional<CompanyResponse> getMyCompany(UUID recruiterId) {
        return companyRepository.findByRecruiterId(recruiterId)
                .map(this::mapToResponse);
    }

    @Transactional
    public CompanyResponse create(UUID recruiterId, CompanyRequest request) {
        User recruiter = getRecruiter(recruiterId);

        if (companyRepository.findByRecruiterId(recruiterId).isPresent()) {
            throw new BadRequestException("Recruiter already has a company profile");
        }

        Company company = new Company();
        company.setRecruiter(recruiter);
        applyRequest(company, request);

        return mapToResponse(companyRepository.save(company));
    }

    @Transactional
    public CompanyResponse update(UUID recruiterId, UUID companyId, CompanyRequest request) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", companyId));

        if (company.getRecruiter() == null || !company.getRecruiter().getId().equals(recruiterId)) {
            throw new AccessDeniedException("Not your company profile");
        }

        applyRequest(company, request);
        company.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(companyRepository.save(company));
    }

    private User getRecruiter(UUID recruiterId) {
        User recruiter = userRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFoundException("User", recruiterId));

        if (recruiter.getRole() != Role.RECRUITER) {
            throw new AccessDeniedException("Only recruiters can manage company profiles");
        }

        return recruiter;
    }

    private void applyRequest(Company company, CompanyRequest request) {
        company.setName(request.getName());
        company.setWebsiteUrl(request.getWebsiteUrl());
        company.setLogoUrl(request.getLogoUrl());
        company.setDescription(request.getDescription());
    }

    private CompanyResponse mapToResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getWebsiteUrl(),
                company.getLogoUrl(),
                company.getDescription()
        );
    }
}
