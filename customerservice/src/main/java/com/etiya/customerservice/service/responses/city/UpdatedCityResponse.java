package com.etiya.customerservice.service.responses.city;

import java.util.UUID;

public class UpdatedCityResponse {
    UUID id;
    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdatedCityResponse() {
    }

    public UpdatedCityResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}