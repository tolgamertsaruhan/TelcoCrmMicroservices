package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.util.UUID;

public class BasketItem implements Serializable {
    private String id;
    private String productId;
    private String productName;
    private double price;
    private String productOfferId;
    private String campaignProductId;
    private double discountRate;
    private int quantity;
    private double discountedPrice;

    public BasketItem(String id, String productId, String productName, double price, String productOfferId, String campaignProductId, double discountRate, int quantity, double discountedPrice) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productOfferId = productOfferId;
        this.campaignProductId = campaignProductId;
        this.discountRate = discountRate;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
    }

    public BasketItem(String id, String productId, String productName, double price) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public BasketItem() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
