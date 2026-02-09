package com.easyhire.easyhire_backend.repository;

import com.easyhire.easyhire_backend.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    Optional<Interview> findByApplication_ApplicationId(Long applicationId);
}