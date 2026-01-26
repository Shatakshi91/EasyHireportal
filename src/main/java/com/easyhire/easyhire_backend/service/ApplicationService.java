package com.easyhire.easyhire_backend.service;

import com.easyhire.easyhire_backend.dto.application.ApplicationCreateRequest;
import com.easyhire.easyhire_backend.dto.application.ApplicationResponse;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse applyToJob(ApplicationCreateRequest request);

    List<ApplicationResponse> getApplicationsForJob(Long jobId);
}
