package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Basket implements Serializable {
    private String id;
    private String billinAccountId;
    private double totalPrice;
    private List<BasketItem> basketItems;

    public Basket(String id, String billinAccountId, double totalPrice, List<BasketItem> basketItems) {
        this.id = id;
        this.billinAccountId = billinAccountId;
        this.totalPrice = totalPrice;
        this.basketItems = basketItems;
    }

    public Basket() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillinAccountId() {
        return billinAccountId;
    }

    public void setBillinAccountId(String billinAccountId) {
        this.billinAccountId = billinAccountId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }
}
