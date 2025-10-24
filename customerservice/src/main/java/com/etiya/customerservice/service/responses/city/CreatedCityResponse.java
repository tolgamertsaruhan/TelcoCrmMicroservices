package com.etiya.customerservice.service.responses.city;

import java.util.UUID;

public class CreatedCityResponse {
    private UUID id;
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreatedCityResponse() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CreatedCityResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}