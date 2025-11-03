package com.etiya.catalogservice.service.requests.product;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateProductRequest {
    private UUID Id;
    private String name;
    private String description;
    private BigDecimal price;
    private UUID catalogId;
    private int stock;
    private UUID specificationId;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UUID getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(UUID catalogId) {
        this.catalogId = catalogId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public UUID getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(UUID specificationId) {
        this.specificationId = specificationId;
    }

    public UpdateProductRequest(UUID id, String name, String description, BigDecimal price, UUID catalogId, int stock, UUID specificationId) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.catalogId = catalogId;
        this.stock = stock;
        this.specificationId = specificationId;
    }

    public UpdateProductRequest() {
    }
}
