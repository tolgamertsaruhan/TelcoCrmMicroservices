package com.etiya.catalogservice.service.responses.product;

import java.math.BigDecimal;
import java.util.UUID;

public class GetProductResponse {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public GetProductResponse(UUID specificationId, int stock, UUID catalogId, BigDecimal price, String description, String name, UUID id) {
        this.specificationId = specificationId;
        this.stock = stock;
        this.catalogId = catalogId;
        this.price = price;
        this.description = description;
        this.name = name;
        Id = id;
    }

    public GetProductResponse() {
    }
}
