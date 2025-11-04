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

    public BasketRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.basketHashOperations = redisTemplate.opsForHash();
    }

    public void add(Basket  basket) {
        this.basketHashOperations.put(Key,basket.getId() + "_" + basket.getBillingAccountId(), basket);
    }

    public Basket getBasketByBillingAccountId(UUID billingAccountId) {
        return basketHashOperations.entries(Key).values().stream().filter(basket -> billingAccountId.equals(basket.getBillingAccountId())).findFirst().orElse(null);
    }

    public Map<String, Basket> getBaskets() {
        return this.basketHashOperations.entries(Key);
    }

    public void clearBasket(UUID billingAccountId) {
        Basket basket = getBasketByBillingAccountId(billingAccountId);
        if (basket != null) {
            basketHashOperations.delete(Key, basket.getId() + "_" + basket.getBillingAccountId());
        }
    }

    public void clearBasketItems(UUID billingAccountId) {
        Basket basket = getBasketByBillingAccountId(billingAccountId);
        if (basket != null) {
            basket.getBasketItems().clear();
            basket.setTotalPrice(null); //önyüzde 0 yazdırırız
            add(basket);
        }
    }
}
