package com.etiya.customerservice.service.requests.district;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class CreateDistrictRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Description can't be longer than 255 characters")
    private String name;

    @NotNull(message = "CityId is required")
    private UUID cityId;

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

    public CreateDistrictRequest() {
    }

    public CreateDistrictRequest(String name, UUID cityId) {
        this.name = name;
        this.cityId = cityId;
    }
}
