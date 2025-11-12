package com.etiya.catalogservice.service.requests.prodCharValue;

import java.util.UUID;

public class CreateProdCharValueRequest {
    private UUID charValueId; // Hangi değere ait
    private UUID productOfferId;

    public UUID getCharValueId() {
        return charValueId;
    }

    public void setCharValueId(UUID charValueId) {
        this.charValueId = charValueId;
    }

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductId(UUID productId) {
        this.productOfferId = productId;
    }

    public CreateProdCharValueRequest(UUID charValueId, UUID productOfferId) {
        this.charValueId = charValueId;
        this.productOfferId = productOfferId;
    }

    public CreateProdCharValueRequest() {}
}
