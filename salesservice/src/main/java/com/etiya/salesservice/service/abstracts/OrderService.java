package com.etiya.salesservice.service.abstracts;

import com.etiya.salesservice.domain.Order;
import com.etiya.salesservice.service.requests.OrderRequest;
import com.etiya.salesservice.service.responses.OrderResponse;

public interface OrderService {

    String add(OrderRequest orderRequest);

    OrderResponse getById(String id);
}