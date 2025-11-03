package com.etiya.catalogservice.service.requests.campaignProduct;

import java.util.UUID;

public class UpdateCampaignProductRequest {
    private UUID id;
    private UUID productId;
    private UUID campaignId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(UUID campaignId) {
        this.campaignId = campaignId;
    }

    public UpdateCampaignProductRequest(UUID id, UUID productId, UUID campaignId) {
        this.id = id;
        this.productId = productId;
        this.campaignId = campaignId;
    }

    public UpdateCampaignProductRequest() {
    }
}
