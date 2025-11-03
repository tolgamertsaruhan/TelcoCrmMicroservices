package com.etiya.catalogservice.service.requests.charValue;

import com.etiya.catalogservice.domain.entities.Characteristic;

import java.util.UUID;

public class UpdateCharValueRequest {
    private UUID id;
    private String value;
    private UUID characteristicId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public UpdateCharValueRequest(UUID id, String value, UUID characteristicId) {
        this.id = id;
        this.value = value;
        this.characteristicId = characteristicId;
    }

    public UpdateCharValueRequest() {
    }
}
