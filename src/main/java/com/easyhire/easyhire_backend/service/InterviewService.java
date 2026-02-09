package com.easyhire.easyhire_backend.service;

import com.easyhire.easyhire_backend.dto.interview.InterviewCreateRequest;
import com.easyhire.easyhire_backend.dto.interview.InterviewResponse;

public interface InterviewService {

    InterviewResponse scheduleInterview(InterviewCreateRequest request);
}
