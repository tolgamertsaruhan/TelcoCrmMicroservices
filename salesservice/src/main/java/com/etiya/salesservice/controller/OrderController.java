package com.etiya.salesservice.controller;

import com.etiya.salesservice.domain.Order;
import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.requests.OrderRequest;
import com.etiya.salesservice.service.responses.OrderIdResponse;
import com.etiya.salesservice.service.responses.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderIdResponse createOrder(@RequestBody OrderRequest orderRequest) {

        OrderIdResponse orderResponse = new OrderIdResponse();
        orderResponse.setOrderId(orderService.add(orderRequest));

        return  orderResponse;

    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable String id) {
        return orderService.getById(id);
    }
}
