package com.etiya.catalogservice.service.responses.ProductConfigration;

import java.util.List;
import java.util.UUID;

public class ProductCharacteristicResponse {
    private UUID id;
    private String name;
    private String description;
    private String dataType;       // STRING, NUMBER, BOOLEAN
    private String unitOfMeasure;  // Mbps, Ay, Yok
    private boolean required;
    private List<CharacteristicValueResponse> values;

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

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<CharacteristicValueResponse> getValues() {
        return values;
    }

    public void setValues(List<CharacteristicValueResponse> values) {
        this.values = values;
    }

    public ProductCharacteristicResponse() {
    }

    public ProductCharacteristicResponse(UUID id, String name, String description, String dataType, String unitOfMeasure, boolean required, List<CharacteristicValueResponse> values) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.unitOfMeasure = unitOfMeasure;
        this.required = required;
        this.values = values;
    }
}
