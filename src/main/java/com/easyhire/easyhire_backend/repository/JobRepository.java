package com.easyhire.easyhire_backend.repository;

import com.easyhire.easyhire_backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    // ✅ tenant-wise jobs
    List<Job> findByTenantId(String tenantId);

    // ✅ MUST return Optional<Job>
    Optional<Job> findByJobIdAndTenantId(Long jobId, String tenantId);
}
