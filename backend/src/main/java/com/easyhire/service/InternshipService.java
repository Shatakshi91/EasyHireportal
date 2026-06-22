package com.easyhire.service;

import com.easyhire.dto.CreateInternshipRequest;
import com.easyhire.dto.InternshipResponse;
import com.easyhire.dto.UpdateInternshipStatusRequest;
import com.easyhire.entity.*;
import com.easyhire.exception.BadRequestException;
import com.easyhire.exception.ResourceNotFoundException;
import com.easyhire.repository.CompanyRepository;
import com.easyhire.repository.InternshipRepository;
import com.easyhire.repository.UserRepository;
import com.easyhire.specification.InternshipSpecification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class InternshipService {

    private static final int MAX_PAGE_SIZE = 100;
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "createdAt",
            "updatedAt",
            "title",
            "location",
            "stipendMin",
            "stipendMax",
            "type",
            "status"
    );

    private final InternshipRepository internshipRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public InternshipService(InternshipRepository internshipRepository,
                             UserRepository userRepository,
                             CompanyRepository companyRepository) {
        this.internshipRepository = internshipRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public InternshipResponse create(UUID recruiterId, CreateInternshipRequest request) {

        User recruiter = userRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFoundException("User", recruiterId));

        Internship internship = new Internship();
        internship.setRecruiter(recruiter);

        if (request.getCompanyId() != null) {
            internship.setCompany(getRecruiterCompany(recruiterId, request.getCompanyId()));
        }

        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setLocation(request.getLocation());
        internship.setStipendMin(request.getStipendMin());
        internship.setStipendMax(request.getStipendMax());
        internship.setType(request.getType());
        internship.setStatus(InternshipStatus.DRAFT);
        internship.setCreatedAt(LocalDateTime.now());
        internship.setUpdatedAt(LocalDateTime.now());

        Internship saved = internshipRepository.save(internship);

        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<InternshipResponse> search(
            String keyword,
            List<InternshipStatus> status,
            InternshipType type,
            String location,
            BigDecimal minStipend,
            BigDecimal maxStipend,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        validateSearchRequest(minStipend, maxStipend, page, size, sortBy, direction);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Internship> spec = Specification
                .where(InternshipSpecification.titleContains(keyword))
                .and(InternshipSpecification.hasStatus(status))
                .and(InternshipSpecification.hasType(type))
                .and(InternshipSpecification.hasLocation(location))
                .and(InternshipSpecification.hasStipendGreaterEqual(minStipend))
                .and(InternshipSpecification.hasStipendLessEqual(maxStipend));

        return internshipRepository.findAll(spec, pageable)
                .map(this::mapToResponse);
    }

    public InternshipResponse updateStatus(UUID recruiterId,
                                           UUID internshipId,
                                           UpdateInternshipStatusRequest request) {

        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", internshipId));

        if (!internship.getRecruiter().getId().equals(recruiterId)) {
            throw new AccessDeniedException("Not your internship");
        }

        internship.setStatus(request.getStatus());
        internship.setUpdatedAt(LocalDateTime.now());

        Internship saved = internshipRepository.save(internship);

        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public InternshipResponse getById(UUID id) {

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", id));

        return mapToResponse(internship);
    }

    public InternshipResponse update(UUID recruiterId,
                                     UUID internshipId,
                                     CreateInternshipRequest request) {

        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", internshipId));

        if (!internship.getRecruiter().getId().equals(recruiterId)) {
            throw new AccessDeniedException("Not your internship");
        }

        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setLocation(request.getLocation());
        internship.setStipendMin(request.getStipendMin());
        internship.setStipendMax(request.getStipendMax());
        internship.setType(request.getType());
        if (request.getCompanyId() != null) {
            internship.setCompany(getRecruiterCompany(recruiterId, request.getCompanyId()));
        }
        internship.setUpdatedAt(LocalDateTime.now());

        Internship updated = internshipRepository.save(internship);

        return mapToResponse(updated);
    }

    private void validateSearchRequest(BigDecimal minStipend,
                                       BigDecimal maxStipend,
                                       int page,
                                       int size,
                                       String sortBy,
                                       String direction) {

        if (page < 0) {
            throw new BadRequestException("Page index must be zero or greater");
        }

        if (size < 1 || size > MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must be between 1 and " + MAX_PAGE_SIZE);
        }

        if (sortBy == null || !ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new BadRequestException("Invalid sort field: " + sortBy);
        }

        if (direction == null || (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc"))) {
            throw new BadRequestException("Sort direction must be either asc or desc");
        }

        if (minStipend != null && maxStipend != null && minStipend.compareTo(maxStipend) > 0) {
            throw new BadRequestException("Minimum stipend cannot be greater than maximum stipend");
        }
    }

    private Company getRecruiterCompany(UUID recruiterId, UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", companyId));

        if (company.getRecruiter() == null || !company.getRecruiter().getId().equals(recruiterId)) {
            throw new AccessDeniedException("Not your company profile");
        }

        return company;
    }

    private InternshipResponse mapToResponse(Internship internship) {

        InternshipResponse response = new InternshipResponse();
        response.setId(internship.getId());
        response.setTitle(internship.getTitle());
        response.setDescription(internship.getDescription());
        response.setLocation(internship.getLocation());
        response.setStipendMin(internship.getStipendMin());
        response.setStipendMax(internship.getStipendMax());
        response.setType(internship.getType());
        response.setStatus(internship.getStatus());

        if (internship.getCompany() != null) {
            response.setCompanyId(internship.getCompany().getId());
            response.setCompanyName(internship.getCompany().getName());
            response.setCompanyLogoUrl(internship.getCompany().getLogoUrl());
        }

        return response;
    }
}
