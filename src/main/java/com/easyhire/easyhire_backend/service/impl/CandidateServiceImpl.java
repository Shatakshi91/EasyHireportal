package com.easyhire.easyhire_backend.service.impl;

import com.easyhire.easyhire_backend.dto.candidate.CandidateCreateRequest;
import com.easyhire.easyhire_backend.dto.candidate.CandidateResponse;
import com.easyhire.easyhire_backend.entity.Candidate;
import com.easyhire.easyhire_backend.exception.BadRequestException;
import com.easyhire.easyhire_backend.repository.CandidateRepository;
import com.easyhire.easyhire_backend.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public CandidateResponse createCandidate(CandidateCreateRequest request) {

        candidateRepository.findByEmail(request.getEmail())
                .ifPresent(c -> {
                    throw new BadRequestException("Candidate already exists");
                });

        Candidate candidate = new Candidate();
        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());
        candidate.setPhone(request.getPhone());
        candidate.setResumeUrl(request.getResumeUrl());

        return CandidateResponse.from(candidateRepository.save(candidate));
    }

    @Override
    public List<CandidateResponse> getAllCandidates() {

        return candidateRepository.findAll()
                .stream()
                .map(CandidateResponse::from)
                .collect(Collectors.toList());
    }
}
