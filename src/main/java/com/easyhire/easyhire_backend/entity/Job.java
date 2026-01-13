package com.easyhire.easyhire_backend.entity;

import com.easyhire.easyhire_backend.entity.enums.JobStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String title;
    private String department;
    private String location;
    private String experience;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @Column(nullable = false)
    private String tenantId;

    // getters & setters
}
