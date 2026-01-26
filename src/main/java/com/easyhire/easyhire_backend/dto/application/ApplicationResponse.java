package com.easyhire.easyhire_backend.dto.application;

import com.easyhire.easyhire_backend.entity.Application;

public class ApplicationResponse {

    private Long applicationId;
    private Long jobId;
    private Long candidateId;
    private String status;

    public static ApplicationResponse from(Application app) {
        ApplicationResponse r = new ApplicationResponse();
        r.applicationId = app.getApplicationId();
        r.jobId = app.getJob().getJobId();
        r.candidateId = app.getCandidate().getCandidateId();
        r.status = app.getStatus().name();
        return r;
    }

    // getters

    public Long getApplicationId() {
        return applicationId;
    }

    public Long getJobId() {
        return jobId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public String getStatus() {
        return status;
    }
}
