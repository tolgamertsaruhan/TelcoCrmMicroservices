package com.etiya.catalogservice.service.requests.product;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private UUID catalogId;
    private int stock;
    private UUID specificationId;

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


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public UUID getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(UUID catalogId) {
        this.catalogId = catalogId;
    }

    public UUID getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(UUID specificationId) {
        this.specificationId = specificationId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CreateProductRequest(String name, String description, BigDecimal price, UUID catalogId, int stock, UUID specificationId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.catalogId = catalogId;
        this.stock = stock;
        this.specificationId = specificationId;
    }

    public CreateProductRequest() {
    }
}
