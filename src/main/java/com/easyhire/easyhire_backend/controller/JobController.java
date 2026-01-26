package com.easyhire.easyhire_backend.controller;

import com.easyhire.easyhire_backend.dto.job.JobCreateRequest;
import com.easyhire.easyhire_backend.dto.job.JobResponse;
import com.easyhire.easyhire_backend.security.JwtUtil;
import com.easyhire.easyhire_backend.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;
    private final JwtUtil jwtUtil;

    public JobController(JobService jobService, JwtUtil jwtUtil) {
        this.jobService = jobService;
        this.jwtUtil = jwtUtil;
    }

    private String extractTenantId(String authHeader) {
        return jwtUtil
                .extractClaims(authHeader.substring(7))
                .get("tenantId", String.class);
    }

    @PostMapping
    public JobResponse createJob(
            @RequestBody JobCreateRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        return jobService.createJob(request, extractTenantId(authHeader));
    }

    @GetMapping
    public List<JobResponse> getAllJobs(
            @RequestHeader("Authorization") String authHeader
    ) {
        return jobService.getAllJobs(extractTenantId(authHeader));
    }

    @GetMapping("/{jobId}")
    public JobResponse getJobById(
            @PathVariable Long jobId,
            @RequestHeader("Authorization") String authHeader
    ) {
        return jobService.getJobById(jobId, extractTenantId(authHeader));
    }

    @PutMapping("/{jobId}/close")
    public JobResponse closeJob(
            @PathVariable Long jobId,
            @RequestHeader("Authorization") String authHeader
    ) {
        return jobService.closeJob(jobId, extractTenantId(authHeader));
    }
}
