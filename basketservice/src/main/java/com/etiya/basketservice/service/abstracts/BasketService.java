package com.etiya.basketservice.service.abstracts;

import com.etiya.basketservice.domain.Basket;

import java.util.Map;
import java.util.UUID;

public interface BasketService {
    void add(UUID customerId, UUID productId, UUID productOfferId);
    Map<String, Basket> getAll();
    void clearBasket(UUID billingAccountId);
    void clearBasketItems(UUID billingAccountId);
    Basket getByBillingAccountId(UUID billingAccountId);
}