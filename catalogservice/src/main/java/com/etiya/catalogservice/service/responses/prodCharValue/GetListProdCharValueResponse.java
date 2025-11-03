package com.etiya.catalogservice.service.responses.prodCharValue;

import java.util.UUID;

public class GetListProdCharValueResponse {
    private UUID id;
    private UUID charValueId; // Hangi değere ait
    private UUID productId;

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

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public GetListProdCharValueResponse(UUID id, UUID charValueId, UUID productId) {
        this.id = id;
        this.charValueId = charValueId;
        this.productId = productId;
    }

    public GetListProdCharValueResponse() {}
}
