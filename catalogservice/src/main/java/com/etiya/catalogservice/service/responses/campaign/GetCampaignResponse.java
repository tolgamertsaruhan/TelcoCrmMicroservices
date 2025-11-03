package com.etiya.catalogservice.service.responses.campaign;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class GetCampaignResponse {
    private UUID id;
    private String name;
    private String campaignCode;
    private BigDecimal discountRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public GetCampaignResponse(UUID id, String name, String campaignCode, BigDecimal discountRate, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.campaignCode = campaignCode;
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public GetCampaignResponse() {
    }
}
