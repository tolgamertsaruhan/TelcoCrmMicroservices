package com.etiya.catalogservice.service.requests.catalogProductOffer;

import java.util.UUID;

public class CreateCatalogProductOfferRequest {
    private UUID catalogId; // İlişkili Catalog Entity'si
    private UUID productOfferId;

    public UUID getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(UUID catalogId) {
        this.catalogId = catalogId;
    }

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(UUID productOfferId) {
        this.productOfferId = productOfferId;
    }

    public CreateCatalogProductOfferRequest(UUID catalogId, UUID productOfferId) {
        this.catalogId = catalogId;
        this.productOfferId = productOfferId;
    }

    public CreateCatalogProductOfferRequest() {
    }
}
