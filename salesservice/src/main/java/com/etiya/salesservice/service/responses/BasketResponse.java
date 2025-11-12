package com.etiya.salesservice.service.responses;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class BasketResponse {
    private String id;
    private UUID billingAccountId;
    private List<BasketItemResponse> basketItems;
    private BigDecimal totalPrice;

    public BasketResponse(String id, UUID billingAccountId, List<BasketItemResponse> basketItems, BigDecimal totalPrice) {
        this.id = id;
        this.billingAccountId = billingAccountId;
        this.basketItems = basketItems;
        this.totalPrice = totalPrice;
    }

    public BasketResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(UUID billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public List<BasketItemResponse> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItemResponse> basketItems) {
        this.basketItems = basketItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
