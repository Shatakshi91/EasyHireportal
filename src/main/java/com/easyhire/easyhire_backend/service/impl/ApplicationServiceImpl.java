package com.easyhire.easyhire_backend.service.impl;

import com.easyhire.easyhire_backend.dto.application.ApplicationCreateRequest;
import com.easyhire.easyhire_backend.dto.application.ApplicationResponse;
import com.easyhire.easyhire_backend.entity.Application;
import com.easyhire.easyhire_backend.entity.Candidate;
import com.easyhire.easyhire_backend.entity.Job;
import com.easyhire.easyhire_backend.entity.enums.ApplicationStatus;
import com.easyhire.easyhire_backend.exception.BadRequestException;
import com.easyhire.easyhire_backend.exception.ResourceNotFoundException;
import com.easyhire.easyhire_backend.repository.ApplicationRepository;
import com.easyhire.easyhire_backend.repository.CandidateRepository;
import com.easyhire.easyhire_backend.repository.JobRepository;
import com.easyhire.easyhire_backend.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;

    public ApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            JobRepository jobRepository,
            CandidateRepository candidateRepository
    ) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public ApplicationResponse applyToJob(ApplicationCreateRequest request) {

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        Candidate candidate = candidateRepository.findById(request.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        if (applicationRepository.existsByJob_JobIdAndCandidate_CandidateId(
                job.getJobId(), candidate.getCandidateId())) {
            throw new BadRequestException("Candidate already applied to this job");
        }

        Application application = new Application();
        application.setJob(job);
        application.setCandidate(candidate);
        application.setStatus(ApplicationStatus.APPLIED);

        return ApplicationResponse.from(applicationRepository.save(application));
    }

    @Override
    public List<ApplicationResponse> getApplicationsForJob(Long jobId) {

        return applicationRepository.findByJob_JobId(jobId)
                .stream()
                .map(ApplicationResponse::from)
                .collect(Collectors.toList());
    }
}
