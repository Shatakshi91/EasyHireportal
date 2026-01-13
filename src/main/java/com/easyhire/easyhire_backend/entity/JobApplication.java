package com.easyhire.easyhire_backend.entity;

import com.easyhire.easyhire_backend.entity.enums.ApplicationStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "job_applications")
public class JobApplication extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id")
    private Job job;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    // getters & setters
}
