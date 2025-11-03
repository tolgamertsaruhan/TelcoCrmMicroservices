package com.etiya.catalogservice.service.responses.charValue;

import com.etiya.catalogservice.domain.entities.Characteristic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class CreatedCharValueResponse {
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

    public CreatedCharValueResponse(UUID id, String value, UUID characteristicId) {
        this.id = id;
        this.value = value;
        this.characteristicId = characteristicId;
    }

    public CreatedCharValueResponse() {
    }
}
