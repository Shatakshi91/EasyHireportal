package com.easyhire.easyhire_backend.dto.application;

public class ApplicationCreateRequest {

    private Long jobId;
    private Long candidateId;

    public Long getJobId() {
        return jobId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
