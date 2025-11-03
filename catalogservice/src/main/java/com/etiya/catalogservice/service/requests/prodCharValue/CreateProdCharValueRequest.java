package com.etiya.catalogservice.service.requests.prodCharValue;

import java.util.UUID;

public class CreateProdCharValueRequest {
    private UUID charValueId; // Hangi değere ait
    private UUID productId;

    public UUID getCharValueId() {
        return charValueId;
    }

    public void setCharValueId(UUID charValueId) {
        this.charValueId = charValueId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public CreateProdCharValueRequest(UUID charValueId, UUID productId) {
        this.charValueId = charValueId;
        this.productId = productId;
    }

    public CreateProdCharValueRequest() {}
}
