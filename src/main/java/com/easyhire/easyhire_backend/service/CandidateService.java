package com.easyhire.easyhire_backend.service;

import com.easyhire.easyhire_backend.dto.candidate.CandidateCreateRequest;
import com.easyhire.easyhire_backend.dto.candidate.CandidateResponse;

import java.util.List;

public interface CandidateService {

    CandidateResponse createCandidate(CandidateCreateRequest request);

    List<CandidateResponse> getAllCandidates();
}
