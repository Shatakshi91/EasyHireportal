package com.easyhire.dto;

import java.util.UUID;

public class CompanyResponse {

    private UUID id;
    private String name;
    private String websiteUrl;
    private String logoUrl;
    private String description;

    public CompanyResponse(UUID id, String name, String websiteUrl, String logoUrl, String description) {
        this.id = id;
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.logoUrl = logoUrl;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getDescription() {
        return description;
    }
}
