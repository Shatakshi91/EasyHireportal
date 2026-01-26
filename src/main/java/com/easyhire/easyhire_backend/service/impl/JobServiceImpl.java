package com.easyhire.easyhire_backend.service.impl;

import com.easyhire.easyhire_backend.dto.job.JobCreateRequest;
import com.easyhire.easyhire_backend.dto.job.JobResponse;
import com.easyhire.easyhire_backend.entity.Job;
import com.easyhire.easyhire_backend.entity.enums.JobStatus;
import com.easyhire.easyhire_backend.exception.BadRequestException;
import com.easyhire.easyhire_backend.exception.ResourceNotFoundException;
import com.easyhire.easyhire_backend.repository.JobRepository;
import com.easyhire.easyhire_backend.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public JobResponse createJob(JobCreateRequest request, String tenantId) {

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDepartment(request.getDepartment());
        job.setLocation(request.getLocation());
        job.setExperience(request.getExperience());
        job.setStatus(JobStatus.OPEN);
        job.setTenantId(tenantId);

        return JobResponse.from(jobRepository.save(job));
    }

    @Override
    public List<JobResponse> getAllJobs(String tenantId) {

        return jobRepository.findByTenantId(tenantId)
                .stream()
                .map(JobResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse getJobById(Long jobId, String tenantId) {

        Job job = jobRepository.findByJobIdAndTenantId(jobId, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        return JobResponse.from(job);
    }

    @Override
    public JobResponse closeJob(Long jobId, String tenantId) {

        Job job = jobRepository.findByJobIdAndTenantId(jobId, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (job.getStatus() == JobStatus.CLOSED) {
            throw new BadRequestException("Job already closed");
        }

        job.setStatus(JobStatus.CLOSED);
        return JobResponse.from(jobRepository.save(job));
    }
}
