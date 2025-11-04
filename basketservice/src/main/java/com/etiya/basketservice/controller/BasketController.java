package com.etiya.basketservice.controller;

import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.service.abstracts.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestParam UUID billingAccountId, @RequestParam UUID productId){
        basketService.add(billingAccountId,productId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Basket> getAll(){
        return basketService.getAll();
    }

    @DeleteMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearBasket(@RequestParam UUID billingAccountId) {
        basketService.clearBasket(billingAccountId);
    }

    @DeleteMapping("/clear-items")
    @ResponseStatus(HttpStatus.OK)
    public void clearBasketItems(@RequestParam UUID billingAccountId) {
        basketService.clearBasketItems(billingAccountId);
    }

    @GetMapping("/{billingAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public Basket getByBillingAccountId(@PathVariable UUID billingAccountId) {
        return basketService.getByBillingAccountId(billingAccountId);
    }
}