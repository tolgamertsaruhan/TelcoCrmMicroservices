package com.etiya.catalogservice.service.requests.productSpecification;

import com.etiya.catalogservice.domain.enums.ProductSpecificationLifeCycleStatuses;

import java.util.UUID;

public class CreateProductSpecificationRequest {
    private String name;
    private String description; // text -> String
    private ProductSpecificationLifeCycleStatuses lifecycleStatus; // Veya Enum (LifecycleStatus) kullanılabilir
    private String productType;

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

    public ProductSpecificationLifeCycleStatuses getLifecycleStatus() {
        return lifecycleStatus;
    }

    public void setLifecycleStatus(ProductSpecificationLifeCycleStatuses lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public CreateProductSpecificationRequest(String name, String description, ProductSpecificationLifeCycleStatuses lifecycleStatus, String productType) {
        this.name = name;
        this.description = description;
        this.lifecycleStatus = lifecycleStatus;
        this.productType = productType;
    }

    public CreateProductSpecificationRequest() {
    }
}
