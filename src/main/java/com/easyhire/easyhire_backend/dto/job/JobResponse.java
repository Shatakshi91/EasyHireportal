package com.easyhire.easyhire_backend.dto.job;

import com.easyhire.easyhire_backend.entity.Job;

public class JobResponse {

    private Long jobId;
    private String title;
    private String department;
    private String location;
    private String experience;
    private String status;

    public static JobResponse from(Job job) {
        JobResponse r = new JobResponse();
        r.jobId = job.getJobId();
        r.title = job.getTitle();
        r.department = job.getDepartment();
        r.location = job.getLocation();
        r.experience = job.getExperience();
        r.status = job.getStatus().name();
        return r;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }

    public String getLocation() {
        return location;
    }

    public String getExperience() {
        return experience;
    }

    public String getStatus() {
        return status;
    }
}
