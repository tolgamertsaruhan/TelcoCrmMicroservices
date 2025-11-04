package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Basket implements Serializable {
    private String id;
    private UUID billingAccountId;
    private BigDecimal totalPrice;
    private List<BasketItem> basketItems;

    public Basket(String id, UUID billingAccountId, BigDecimal totalPrice, List<BasketItem> basketItems) {
        this.id = id;
        this.billingAccountId = billingAccountId;
        this.totalPrice = totalPrice;
        this.basketItems = basketItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public UUID getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(UUID billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Basket(){
        this.id= UUID.randomUUID().toString();
        this.basketItems = new ArrayList<>();
    }
}
