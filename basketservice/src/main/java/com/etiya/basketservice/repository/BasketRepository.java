package com.etiya.basketservice.repository;

import com.etiya.basketservice.domain.Basket;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class BasketRepository {
    public static final String Key = "Basket";
    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, Basket> basketHashOperations;

    public BasketRepository(RedisTemplate<String, Object> redisTemplate, HashOperations<String, String, Basket> basketHashOperations) {
        this.redisTemplate = redisTemplate;
        this.basketHashOperations = basketHashOperations;
    }

    public void add(Basket  basket) {
        this.basketHashOperations.put(Key,basket.getId() + "_" + basket.getBillinAccountId(), basket);
    }

    public Basket getBasketByBillingAccountId(UUID billingAccountId) {
        return basketHashOperations.entries(Key).values().stream().filter(basket -> billingAccountId.equals(basket.getBillinAccountId())).findFirst().orElse(null);
    }

    public Map<String, Basket> getBaskets() {
        return this.basketHashOperations.entries(Key);
    }
}
