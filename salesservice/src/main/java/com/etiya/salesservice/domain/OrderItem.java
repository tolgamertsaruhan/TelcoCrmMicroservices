package com.etiya.salesservice.domain;

import org.springframework.data.mongodb.core.mapping.Field;

public class OrderItem {

    @Field(name = "id")
    private String id;

    @Field(name = "productId")
    private String productId;

    @Field(name = "campaignProductId")
    private String campaignProductId;

    @Field(name = "productOfferId")
    private String productOfferId;

    @Field(name = "sourceType")
    private String sourceType;

    @Field(name = "productName")
    private String productName;

    @Field(name = "price")
    private double price;

    @Field(name = "discountedPrice")
    private double discountedPrice;

    @Field(name = "discountRate")
    private double discountRate;

    public String getCampaignProductId() {
        return campaignProductId;
    }

    public void setCampaignProductId(String campaignProductId) {
        this.campaignProductId = campaignProductId;
    }

    public String getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(String productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

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
