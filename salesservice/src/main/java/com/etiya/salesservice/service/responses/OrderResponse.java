package com.etiya.salesservice.service.responses;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {

    private String orderId;
    private BigDecimal totalAmount;
    private String serviceAddress;
    private List<OrderItemResponse> orderItems;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderResponse() {
    }

    public OrderResponse(String orderId, BigDecimal totalAmount, String serviceAddress, List<OrderItemResponse> orderItems) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.serviceAddress = serviceAddress;
        this.orderItems = orderItems;
    }
}