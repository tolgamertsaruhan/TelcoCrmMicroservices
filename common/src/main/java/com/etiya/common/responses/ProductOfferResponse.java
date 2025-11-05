package com.etiya.common.responses;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductOfferResponse {
    private UUID id;
    BigDecimal discountRate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
}
