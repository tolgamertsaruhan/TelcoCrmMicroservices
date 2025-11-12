package com.etiya.catalogservice.service.requests.product;

import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private UUID catalogId;
    private UUID productOfferId;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(UUID productOfferId) {
        this.productOfferId = productOfferId;
    }

    public CreateProductRequest(String name, String description, BigDecimal price, BigDecimal discountedPrice, UUID catalogId, UUID productOfferId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.catalogId = catalogId;
        this.productOfferId = productOfferId;
    }

    public CreateProductRequest() {
    }
}
