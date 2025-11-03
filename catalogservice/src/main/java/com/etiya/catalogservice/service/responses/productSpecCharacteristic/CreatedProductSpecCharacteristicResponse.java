package com.etiya.catalogservice.service.responses.productSpecCharacteristic;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.ProductSpecification;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class CreatedProductSpecCharacteristicResponse {
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

    public CreatedProductSpecCharacteristicResponse(UUID id, boolean requiredIs, UUID productSpecificationId, UUID characteristicId) {
        this.id = id;
        this.requiredIs = requiredIs;
        this.productSpecificationId = productSpecificationId;
        this.characteristicId = characteristicId;
    }

    public CreatedProductSpecCharacteristicResponse() {
    }
}
