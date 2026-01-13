package com.easyhire.easyhire_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.easyhire.easyhire_backend.entity.JobApplication;

import com.easyhire.easyhire_backend.entity.Interview;


import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findByApplication(JobApplication application);
}
