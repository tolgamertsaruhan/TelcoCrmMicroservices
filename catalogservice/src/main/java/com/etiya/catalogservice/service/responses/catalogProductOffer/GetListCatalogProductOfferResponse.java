package com.etiya.catalogservice.service.responses.catalogProductOffer;

import java.util.UUID;

public class GetListCatalogProductOfferResponse {
    private UUID id;
    private UUID catalogId; // İlişkili Catalog Entity'si
    private UUID productOfferId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public GetListCatalogProductOfferResponse(UUID id, UUID catalogId, UUID productOfferId) {
        this.id = id;
        this.catalogId = catalogId;
        this.productOfferId = productOfferId;
    }

    public GetListCatalogProductOfferResponse() {
    }
}
