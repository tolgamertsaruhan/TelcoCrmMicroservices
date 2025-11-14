package com.etiya.salesservice.service.responses;

public class OrderIdResponse {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderIdResponse() {
    }

    public OrderIdResponse(String orderId) {
        this.orderId = orderId;
    }
}
