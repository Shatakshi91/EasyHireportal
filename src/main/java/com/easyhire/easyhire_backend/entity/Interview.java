package com.easyhire.easyhire_backend.entity;

import com.easyhire.easyhire_backend.entity.enums.InterviewStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false)
    private User interviewer;

    public User getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(User interviewer) {
        this.interviewer = interviewer;
    }

    // 🔴 THIS WAS WRONG EARLIER
    @OneToOne(optional = false)
    @JoinColumn(name = "application_id", unique = true)
    private Application application;

    private LocalDateTime scheduledAt;

    private String mode;

    @Enumerated(EnumType.STRING)
    private InterviewStatus status;

    // ✅ ADD ALL SETTERS (THEY WERE MISSING)

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }

    // getters (optional but recommended)
    public Long getInterviewId() {
        return interviewId;
    }

    public Application getApplication() {
        return application;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public String getMode() {
        return mode;
    }

    public InterviewStatus getStatus() {
        return status;
    }
}
