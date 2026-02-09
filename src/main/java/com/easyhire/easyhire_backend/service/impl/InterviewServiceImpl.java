package com.easyhire.easyhire_backend.service.impl;

import com.easyhire.easyhire_backend.dto.interview.InterviewCreateRequest;
import com.easyhire.easyhire_backend.dto.interview.InterviewResponse;
import com.easyhire.easyhire_backend.entity.Application;
import com.easyhire.easyhire_backend.entity.Interview;
import com.easyhire.easyhire_backend.entity.User;
import com.easyhire.easyhire_backend.entity.enums.InterviewStatus;
import com.easyhire.easyhire_backend.exception.BadRequestException;
import com.easyhire.easyhire_backend.exception.ResourceNotFoundException;
import com.easyhire.easyhire_backend.repository.ApplicationRepository;
import com.easyhire.easyhire_backend.repository.InterviewRepository;
import com.easyhire.easyhire_backend.repository.UserRepository;
import com.easyhire.easyhire_backend.service.InterviewService;
import org.springframework.stereotype.Service;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    public InterviewServiceImpl(
            InterviewRepository interviewRepository,
            ApplicationRepository applicationRepository,
            UserRepository userRepository
    ) {
        this.interviewRepository = interviewRepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public InterviewResponse scheduleInterview(InterviewCreateRequest request) {

        Application application = applicationRepository
                .findByApplicationId(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        interviewRepository.findByApplication_ApplicationId(application.getApplicationId())
                .ifPresent(i -> {
                    throw new BadRequestException("Interview already scheduled");
                });
        User interviewer;
        if (request.getInterviewerId() != null) {
            interviewer = userRepository.findById(request.getInterviewerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Interviewer not found"));
        } else {
            String email = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
            interviewer = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Interviewer not found (User not logged in)"));
        }

        Interview interview = new Interview();
        interview.setApplication(application);
        interview.setScheduledAt(request.getScheduledAt());
        interview.setMode(request.getMode());
        interview.setStatus(InterviewStatus.SCHEDULED);
        interview.setInterviewer(interviewer); // 🔥 THIS WAS MISSING

        return InterviewResponse.from(interviewRepository.save(interview));
    }
}
