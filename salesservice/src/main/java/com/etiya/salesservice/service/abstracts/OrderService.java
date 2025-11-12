package com.etiya.salesservice.service.abstracts;

import com.etiya.salesservice.service.requests.OrderRequest;

public interface OrderService {

    void add(OrderRequest orderRequest);
}