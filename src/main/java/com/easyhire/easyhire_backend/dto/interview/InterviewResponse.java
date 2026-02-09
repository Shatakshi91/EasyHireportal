package com.easyhire.easyhire_backend.dto.interview;


import com.easyhire.easyhire_backend.entity.Interview;

import java.time.LocalDateTime;

public class InterviewResponse {

    private Long interviewId;
    private Long applicationId;
    private LocalDateTime scheduledAt;
    private String mode;
    private String status;

    public static InterviewResponse from(Interview i) {
        InterviewResponse r = new InterviewResponse();
        r.interviewId = i.getInterviewId();
        r.applicationId = i.getApplication().getApplicationId();
        r.scheduledAt = i.getScheduledAt();
        r.mode = i.getMode();
        r.status = i.getStatus().name();
        return r;
    }

    // getters
    public Long getInterviewId() { return interviewId; }
    public Long getApplicationId() { return applicationId; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public String getMode() { return mode; }
    public String getStatus() { return status; }


}
