package com.easyhire.easyhire_backend.repository;

import com.easyhire.easyhire_backend.entity.Job;
import com.easyhire.easyhire_backend.entity.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByTenantId(String tenantId);

    List<Job> findByStatus(JobStatus status);
}