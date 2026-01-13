package com.easyhire.easyhire_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id")
    private JobApplication application;

    @ManyToOne(optional = false)
    @JoinColumn(name = "interviewer_id")
    private User interviewer;

    private LocalDateTime scheduledAt;

    private String feedback;

    private Integer rating;

    // getters & setters
}