package com.easyhire.easyhire_backend.repository;

import com.easyhire.easyhire_backend.entity.Candidate;
import com.easyhire.easyhire_backend.entity.Job;
import com.easyhire.easyhire_backend.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByJob(Job job);

    List<JobApplication> findByCandidate(Candidate candidate);
}