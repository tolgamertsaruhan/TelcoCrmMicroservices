package com.etiya.basketservice.controller;

import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.service.abstracts.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void add(@RequestParam UUID billingAccountId, @RequestParam UUID productId, @RequestParam UUID productOfferId) {
        basketService.add(billingAccountId,productId,productOfferId);
    }
    @PostMapping("/add")
    public ResponseEntity<Void> addToBasket(
            @RequestParam UUID billingAccountId,
            @RequestParam(required = false) UUID productOfferId,
            @RequestParam(required = false) UUID campaignId) {

        basketService.add(billingAccountId, productOfferId, campaignId);
        return ResponseEntity.ok().build();
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

    // 🔴 Sepetten ürün çıkarma / quantity azaltma
    @DeleteMapping("/remove")
    public ResponseEntity<Basket> removeFromBasket(
            @RequestParam UUID billingAccountId,
            @RequestParam(required = false) UUID productOfferId,
            @RequestParam(required = false) UUID campaignId
    ) {
        basketService.removeItem(billingAccountId, productOfferId, campaignId);
        Basket basket = basketService.getByBillingAccountId(billingAccountId);
        return ResponseEntity.ok(basket);
    }
}