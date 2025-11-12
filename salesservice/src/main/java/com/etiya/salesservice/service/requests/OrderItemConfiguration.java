package com.etiya.salesservice.service.requests;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class OrderItemConfiguration {
    private UUID productOfferId;
    private String productName;
    private BigDecimal price;
    private Map<String, String> configurationValues;

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(UUID productOfferId) {
        this.productOfferId = productOfferId;
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

    public Map<String, String> getConfigurationValues() {
        return configurationValues;
    }

    public void setConfigurationValues(Map<String, String> configurationValues) {
        this.configurationValues = configurationValues;
    }

    public OrderItemConfiguration(UUID productOfferId, String productName, BigDecimal price, Map<String, String> configurationValues) {
        this.productOfferId = productOfferId;
        this.productName = productName;
        this.price = price;
        this.configurationValues = configurationValues;
    }

    public OrderItemConfiguration() {
    }
}
