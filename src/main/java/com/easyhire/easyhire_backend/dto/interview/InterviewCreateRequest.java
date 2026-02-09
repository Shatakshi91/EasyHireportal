package com.easyhire.easyhire_backend.dto.interview;

import java.time.LocalDateTime;

public class InterviewCreateRequest {

    private Long applicationId;
    private LocalDateTime scheduledAt;
    private String mode;
    private long interviewId;
    private Long interviewerId;

    public Long getApplicationId() {
        return applicationId;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public String getMode() {
        return mode;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setInterviewId(long interviewId) {
        this.interviewId = interviewId;
    }

    public long getInterviewId() {
        return interviewId;
    }

    public Long getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(Long interviewerId) {
        this.interviewerId = interviewerId;
    }
}
