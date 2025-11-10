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
    private BigDecimal price;
    private ProductOfferStatuses status;

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

    public ProductOfferStatuses getStatus() {
        return status;
    }

    public void setStatus(ProductOfferStatuses status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UpdatedProductOfferResponse(UUID id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price, ProductOfferStatuses status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.status = status;
    }

    public UpdatedProductOfferResponse() {
    }
}
