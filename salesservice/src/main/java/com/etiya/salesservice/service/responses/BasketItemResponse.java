package com.etiya.salesservice.service.responses;

import java.math.BigDecimal;
import java.util.UUID;

public class BasketItemResponse {
    private String id;
    private UUID campaignProductOfferId;
    private String productName;
    private BigDecimal price;
    private UUID productOfferId;
    private UUID campaignId;
    private BigDecimal discountRate;
    private int quantity;
    private BigDecimal discountedPrice;

    public BasketItemResponse(String id, UUID campaignProductOfferId, String productName, BigDecimal price, UUID productOfferId, UUID campaignId, BigDecimal discountRate, int quantity, BigDecimal discountedPrice) {
        this.id = id;
        this.campaignProductOfferId = campaignProductOfferId;
        this.productName = productName;
        this.price = price;
        this.productOfferId = productOfferId;
        this.campaignId = campaignId;
        this.discountRate = discountRate;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
    }

    public BasketItemResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public UUID getCampaignProductOfferId() {
        return campaignProductOfferId;
    }

    public void setCampaignProductOfferId(UUID campaignProductOfferId) {
        this.campaignProductOfferId = campaignProductOfferId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(UUID productOfferId) {
        this.productOfferId = productOfferId;
    }

    public UUID getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(UUID campaignId) {
        this.campaignId = campaignId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
