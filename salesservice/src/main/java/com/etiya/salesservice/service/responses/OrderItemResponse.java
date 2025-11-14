package com.etiya.salesservice.service.responses;

import java.math.BigDecimal;
import java.util.Map;

public class OrderItemResponse {
    private String productName;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private Map<String, String> configurationValues;

    public OrderItemResponse() {
    }

    public OrderItemResponse(String productName, BigDecimal price, BigDecimal discountedPrice, Map<String, String> configurationValues) {
        this.productName = productName;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.configurationValues = configurationValues;
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

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Map<String, String> getConfigurationValues() {
        return configurationValues;
    }

    public void setConfigurationValues(Map<String, String> configurationValues) {
        this.configurationValues = configurationValues;
    }
}
