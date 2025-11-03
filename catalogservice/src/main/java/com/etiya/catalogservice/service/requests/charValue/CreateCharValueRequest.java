package com.etiya.catalogservice.service.requests.charValue;

import com.etiya.catalogservice.domain.entities.Characteristic;

import java.util.UUID;

public class CreateCharValueRequest {
    private String value;
    private UUID characteristicId;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(UUID characteristicId) {
        this.characteristicId = characteristicId;
    }

    public CreateCharValueRequest(String value, UUID characteristicId) {
        this.value = value;
        this.characteristicId = characteristicId;
    }

    public CreateCharValueRequest() {
    }
}
