package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class BasketItem implements Serializable {
    private String id;
    private UUID productId;
    private String productName;
    private BigDecimal price;
    private String productOfferId;
    private String campaignProductId;
    private BigDecimal discountRate;
    private int quantity;
    private BigDecimal discountedPrice;

    public BasketItem(String id, UUID productId, String productName, BigDecimal price, String productOfferId, String campaignProductId, BigDecimal discountRate, int quantity, BigDecimal discountedPrice) {
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

    public BasketItem(String id, UUID productId, String productName, BigDecimal price) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public BasketItem() {
        this.id = UUID.randomUUID().toString();
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(String productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getCampaignProductId() {
        return campaignProductId;
    }

    public void setCampaignProductId(String campaignProductId) {
        this.campaignProductId = campaignProductId;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
