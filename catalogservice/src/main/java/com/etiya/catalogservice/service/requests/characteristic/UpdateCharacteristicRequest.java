package com.etiya.catalogservice.service.requests.characteristic;

import java.util.UUID;

public class UpdateCharacteristicRequest {
    private UUID id;
    private String name;
    private String description;
    private String dataType;
    private String unitOfMeasure;

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

    public UpdateCharacteristicRequest(UUID id, String name, String description, String dataType, String unitOfMeasure) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.unitOfMeasure = unitOfMeasure;
    }

    public UpdateCharacteristicRequest() {
    }
}
