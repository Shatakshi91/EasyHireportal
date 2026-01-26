package com.easyhire.easyhire_backend.controller;

import com.easyhire.easyhire_backend.dto.application.ApplicationCreateRequest;
import com.easyhire.easyhire_backend.dto.application.ApplicationResponse;
import com.easyhire.easyhire_backend.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // PUBLIC (Candidate applies)
    @PostMapping
    public ApplicationResponse applyToJob(
            @RequestBody ApplicationCreateRequest request
    ) {
        return applicationService.applyToJob(request);
    }

    // SECURED (HR/Admin)
    @GetMapping("/job/{jobId}")
    public List<ApplicationResponse> getApplicationsForJob(
            @PathVariable Long jobId
    ) {
        return applicationService.getApplicationsForJob(jobId);
    }
}
