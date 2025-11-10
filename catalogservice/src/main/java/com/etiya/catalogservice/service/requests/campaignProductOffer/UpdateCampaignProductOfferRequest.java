package com.etiya.catalogservice.service.requests.campaignProductOffer;

import java.util.UUID;

public class UpdateCampaignProductOfferRequest {
    private UUID id;
    private UUID productOfferId;
    private UUID campaignId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public UpdateCampaignProductOfferRequest(UUID id, UUID productOfferId, UUID campaignId) {
        this.id = id;
        this.productOfferId = productOfferId;
        this.campaignId = campaignId;
    }

    public UpdateCampaignProductOfferRequest() {
    }
}
