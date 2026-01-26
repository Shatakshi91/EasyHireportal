package com.easyhire.easyhire_backend.dto.candidate;

import com.easyhire.easyhire_backend.entity.Candidate;

public class CandidateResponse {

    private Long candidateId;
    private String name;
    private String email;
    private String phone;
    private String resumeUrl;

    public static CandidateResponse from(Candidate c) {
        CandidateResponse r = new CandidateResponse();
        r.candidateId = c.getCandidateId();
        r.name = c.getName();
        r.email = c.getEmail();
        r.phone = c.getPhone();
        r.resumeUrl = c.getResumeUrl();
        return r;
    }

    // getters

    public Long getCandidateId() {
        return candidateId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }
}
