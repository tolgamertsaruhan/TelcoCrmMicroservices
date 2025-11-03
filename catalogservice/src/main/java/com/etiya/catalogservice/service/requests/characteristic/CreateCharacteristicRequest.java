package com.etiya.catalogservice.service.requests.characteristic;

import java.util.UUID;

public class CreateCharacteristicRequest {
    private String name;
    private String description;
    private String dataType;
    private String unitOfMeasure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public CreateCharacteristicRequest(String name, String description, String dataType, String unitOfMeasure) {
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.unitOfMeasure = unitOfMeasure;
    }

    public CreateCharacteristicRequest() {
    }
}
