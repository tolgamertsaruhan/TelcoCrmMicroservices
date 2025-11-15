package com.etiya.salesservice.service.abstracts;

import com.etiya.salesservice.domain.Order;
import com.etiya.salesservice.service.requests.OrderRequest;
import com.etiya.salesservice.service.responses.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    String add(OrderRequest orderRequest);

    OrderResponse getById(String id);

    UUID getBillingAccountIdByOrderId(String orderId);

    List<OrderResponse> getOrdersByBillingAccountId(String billingAccountId);
}