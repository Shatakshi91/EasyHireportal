package com.easyhire.easyhire_backend.entity;

import com.easyhire.easyhire_backend.entity.enums.ApplicationStatus;
import jakarta.persistence.*;

@Entity
@Table(
        name = "applications",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"job_id", "candidate_id"})
        }
)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    // getters & setters

    public Long getApplicationId() {
        return applicationId;
    }

    public Job getJob() {
        return job;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
