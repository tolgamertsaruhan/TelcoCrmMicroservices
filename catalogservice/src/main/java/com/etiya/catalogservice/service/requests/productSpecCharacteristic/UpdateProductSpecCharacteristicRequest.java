package com.etiya.catalogservice.service.requests.productSpecCharacteristic;

import java.util.UUID;

public class UpdateProductSpecCharacteristicRequest {
    private UUID id;
    private boolean requiredIs;
    private UUID productSpecificationId;
    private UUID characteristicId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isRequiredIs() {
        return requiredIs;
    }

    public void setRequiredIs(boolean requiredIs) {
        this.requiredIs = requiredIs;
    }

    public UUID getProductSpecificationId() {
        return productSpecificationId;
    }

    public void setProductSpecificationId(UUID productSpecificationId) {
        this.productSpecificationId = productSpecificationId;
    }

    public UUID getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(UUID characteristicId) {
        this.characteristicId = characteristicId;
    }

    public UpdateProductSpecCharacteristicRequest(UUID id, boolean requiredIs, UUID productSpecificationId, UUID characteristicId) {
        this.id = id;
        this.requiredIs = requiredIs;
        this.productSpecificationId = productSpecificationId;
        this.characteristicId = characteristicId;
    }

    public UpdateProductSpecCharacteristicRequest() {
    }
}
