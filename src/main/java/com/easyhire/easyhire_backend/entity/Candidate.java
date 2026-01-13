package com.easyhire.easyhire_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String resumeUrl;

    // getters & setters
}