package com.etiya.salesservice.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class OrderItem {

    @Field(name = "id")
    private String id;

    @Field(name = "productId")
    private String productId;

    @Field(name = "campaignProductId")
    private String campaignProductId;

    @Field(name = "productOfferId")
    private String productOfferId;

    @Field(name = "productName")
    private String productName;

    @Field(name = "price")
    private BigDecimal price;

    @Field(name = "discountedPrice")
    private BigDecimal discountedPrice;

    @Field(name = "discountRate")
    private BigDecimal discountRate;

    @Field(name = "configurationValues")
    private Map<String, String> configurationValues;

    public Map<String, String> getConfigurationValues() {
        return configurationValues;
    }

    public void setConfigurationValues(Map<String, String> configurationValues) {
        this.configurationValues = configurationValues;
    }

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

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderItem(String id, String productId, String campaignProductId, String productOfferId, String productName, BigDecimal price, BigDecimal discountedPrice, BigDecimal discountRate, Map<String, String> configurationValues) {
        this.id = id;
        this.productId = productId;
        this.campaignProductId = campaignProductId;
        this.productOfferId = productOfferId;
        this.productName = productName;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.discountRate = discountRate;
        this.configurationValues = configurationValues;
    }

    public OrderItem() {
    }
}
