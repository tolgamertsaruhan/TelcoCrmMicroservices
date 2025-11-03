package com.etiya.catalogservice.service.requests.campaignProduct;

import java.util.UUID;

public class CreateCampaignProductRequest {
    private UUID productId;
    private UUID campaignId;

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

    public CreateCampaignProductRequest(UUID productId, UUID campaignId) {
        this.productId = productId;
        this.campaignId = campaignId;
    }

    public CreateCampaignProductRequest() {
    }
}
