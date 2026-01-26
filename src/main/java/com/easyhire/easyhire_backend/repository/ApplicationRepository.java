package com.easyhire.easyhire_backend.repository;

import com.easyhire.easyhire_backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByJob_JobIdAndCandidate_CandidateId(Long jobId, Long candidateId);

    List<Application> findByJob_JobId(Long jobId);

    Optional<Application> findByApplicationId(Long applicationId);
}
