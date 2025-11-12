package com.etiya.catalogservice.service.responses.prodCharValue;

import java.util.UUID;

public class GetProdCharValueResponse {
    private UUID id;
    private UUID charValueId; // Hangi değere ait
    private UUID productOfferId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCharValueId() {
        return charValueId;
    }

    public void setCharValueId(UUID charValueId) {
        this.charValueId = charValueId;
    }

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(UUID productOfferId) {
        this.productOfferId = productOfferId;
    }

    public GetProdCharValueResponse(UUID id, UUID charValueId, UUID productOfferId) {
        this.id = id;
        this.charValueId = charValueId;
        this.productOfferId = productOfferId;
    }

    public GetProdCharValueResponse() {}
}
