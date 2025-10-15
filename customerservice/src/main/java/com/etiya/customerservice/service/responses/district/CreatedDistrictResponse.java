package com.etiya.customerservice.service.responses.district;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class CreatedDistrictResponse {
    private UUID id;
    private String name;
    private UUID cityId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    public CreatedDistrictResponse() {
    }

    public CreatedDistrictResponse(UUID id, String name, UUID cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
    }
}
