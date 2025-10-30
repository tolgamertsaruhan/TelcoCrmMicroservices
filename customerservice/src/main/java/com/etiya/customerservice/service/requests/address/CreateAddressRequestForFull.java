package com.etiya.customerservice.service.requests.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class CreateAddressRequestForFull {

    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "House number is required")
    private String houseNumber;
    @Size(max = 255, message = "Description can't be longer than 255 characters")
    private String description;
    private boolean isDefault;
    private UUID districtId;
    private UUID customerId;

    private UUID cityId;

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public CreateAddressRequestForFull() {
    }

    public CreateAddressRequestForFull(String street, String houseNumber, String description, boolean isDefault, UUID districtId, UUID customerId, UUID cityId) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.isDefault = isDefault;
        this.districtId = districtId;
        this.customerId = customerId;
        this.cityId = cityId;
    }
}
