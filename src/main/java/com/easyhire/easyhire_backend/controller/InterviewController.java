package com.easyhire.easyhire_backend.controller;

import com.easyhire.easyhire_backend.dto.interview.InterviewCreateRequest;
import com.easyhire.easyhire_backend.dto.interview.InterviewResponse;
import com.easyhire.easyhire_backend.service.InterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    // HR / ADMIN
    @PostMapping
    public InterviewResponse scheduleInterview(
            @RequestBody InterviewCreateRequest request
    ) {
        return interviewService.scheduleInterview(request);
    }
}
