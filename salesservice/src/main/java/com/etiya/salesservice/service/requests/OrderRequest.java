package com.etiya.salesservice.service.requests;

import java.util.List;
import java.util.UUID;

public class OrderRequest {
    private UUID billingAccountId;
    private UUID addressId;
    private List<OrderItemConfiguration> configurations;

    public OrderRequest() {
    }

    public OrderRequest(UUID billingAccountId, UUID addressId, List<OrderItemConfiguration> configurations) {
        this.billingAccountId = billingAccountId;
        this.addressId = addressId;
        this.configurations = configurations;
    }

    public UUID getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(UUID billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public List<OrderItemConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<OrderItemConfiguration> configurations) {
        this.configurations = configurations;
    }
}
