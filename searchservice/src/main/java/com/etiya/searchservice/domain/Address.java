package com.etiya.searchservice.domain;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


public class Address {

    @Id
    String addressId;
    String customerId;
    String districtName;
    @Field(type = FieldType.Keyword)
    String cityName;
    String street;
    String houseNumber;
    String description;
    boolean isDefault;


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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

    public Address() {
    }


    public String getCityName() {
        return cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Address(String addressId, String customerId, String districtName, String cityName, String street, String houseNumber, String description, boolean isDefault) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.districtName = districtName;
        this.cityName = cityName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.isDefault = isDefault;
    }

    public Address(String addressId, String street, String houseNumber, String description, boolean isDefault) {
        this.addressId = addressId;

        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.isDefault = isDefault;
    }

    public Address(String addressId, String customerId) {
        this.addressId = addressId;
        this.customerId = customerId;
    }
}
