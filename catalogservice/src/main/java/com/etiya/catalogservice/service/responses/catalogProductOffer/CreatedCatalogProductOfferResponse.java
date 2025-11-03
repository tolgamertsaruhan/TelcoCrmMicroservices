package com.etiya.catalogservice.service.responses.catalogProductOffer;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.domain.entities.ProductOffer;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class CreatedCatalogProductOfferResponse {
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

    public CreatedCatalogProductOfferResponse(UUID id, UUID catalogId, UUID productOfferId) {
        this.id = id;
        this.catalogId = catalogId;
        this.productOfferId = productOfferId;
    }

    public CreatedCatalogProductOfferResponse() {
    }
}
