package com.etiya.catalogservice.service.responses.ProductConfigration;

import java.util.UUID;

public class CharacteristicValueResponse {
    private UUID id;
    private String value;

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

    public CharacteristicValueResponse() {
    }

    public CharacteristicValueResponse(UUID id, String value) {
        this.id = id;
        this.value = value;
    }
}