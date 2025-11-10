package com.etiya.catalogservice.service.requests.campaignProductOffer;

import java.util.UUID;

public class CreateCampaignProductOfferRequest {
    private UUID productOfferId;
    private UUID campaignId;

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(UUID productOfferId) {
        this.productOfferId = productOfferId;
    }

    public UUID getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(UUID campaignId) {
        this.campaignId = campaignId;
    }

    public CreateCampaignProductOfferRequest(UUID productOfferId, UUID campaignId) {
        this.productOfferId = productOfferId;
        this.campaignId = campaignId;
    }

    public CreateCampaignProductOfferRequest() {
    }
}
