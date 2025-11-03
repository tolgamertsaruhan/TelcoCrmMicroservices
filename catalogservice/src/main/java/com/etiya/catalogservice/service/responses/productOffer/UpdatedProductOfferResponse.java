package com.etiya.catalogservice.service.responses.productOffer;

import com.etiya.catalogservice.domain.enums.ProductOfferStatuses;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class UpdatedProductOfferResponse {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal discountRate;
    private ProductOfferStatuses status;
    private UUID productId;

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public ProductOfferStatuses getStatus() {
        return status;
    }

    public void setStatus(ProductOfferStatuses status) {
        this.status = status;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UpdatedProductOfferResponse(UUID id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, BigDecimal discountRate, ProductOfferStatuses status, UUID productId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountRate = discountRate;
        this.status = status;
        this.productId = productId;
    }

    public UpdatedProductOfferResponse() {
    }
}
