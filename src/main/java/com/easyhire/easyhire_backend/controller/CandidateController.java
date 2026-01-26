package com.easyhire.easyhire_backend.controller;

import com.easyhire.easyhire_backend.dto.candidate.CandidateCreateRequest;
import com.easyhire.easyhire_backend.dto.candidate.CandidateResponse;
import com.easyhire.easyhire_backend.service.CandidateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // PUBLIC API
    @PostMapping
    public CandidateResponse createCandidate(
            @RequestBody CandidateCreateRequest request
    ) {
        return candidateService.createCandidate(request);
    }

    // SECURED (JWT required)
    @GetMapping
    public List<CandidateResponse> getAllCandidates() {
        return candidateService.getAllCandidates();
    }
}
