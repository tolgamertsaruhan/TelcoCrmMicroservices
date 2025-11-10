package com.etiya.catalogservice.service.responses.product;

import java.math.BigDecimal;
import java.util.UUID;

public class GetListProductResponse {
    private UUID Id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private UUID catalogId;
    private UUID prdouctOfferId;
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

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public UUID getPrdouctOfferId() {
        return prdouctOfferId;
    }

    public void setPrdouctOfferId(UUID prdouctOfferId) {
        this.prdouctOfferId = prdouctOfferId;
    }

    public GetListProductResponse(UUID id, String name, String description, BigDecimal price, BigDecimal discountedPrice, UUID catalogId, UUID prdouctOfferId, UUID specificationId) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.catalogId = catalogId;
        this.prdouctOfferId = prdouctOfferId;
        this.specificationId = specificationId;
    }

    public GetListProductResponse() {
    }
}
