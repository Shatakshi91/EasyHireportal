package com.easyhire.easyhire_backend.service;

import com.easyhire.easyhire_backend.dto.job.JobCreateRequest;
import com.easyhire.easyhire_backend.dto.job.JobResponse;

import java.util.List;

public interface JobService {

    JobResponse createJob(JobCreateRequest request, String tenantId);

    List<JobResponse> getAllJobs(String tenantId);

    JobResponse getJobById(Long jobId, String tenantId);

    JobResponse closeJob(Long jobId, String tenantId);
}
