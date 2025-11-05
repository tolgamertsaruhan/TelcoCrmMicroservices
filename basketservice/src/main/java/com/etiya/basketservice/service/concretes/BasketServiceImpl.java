package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.repository.BasketRepository;
import com.etiya.basketservice.service.abstracts.BasketService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;

    public BasketServiceImpl(BasketRepository basketRepository, CustomerServiceClient customerServiceClient, CatalogServiceClient catalogServiceClient) {
        this.basketRepository = basketRepository;
        this.customerServiceClient = customerServiceClient;
        this.catalogServiceClient = catalogServiceClient;
    }

    @Override
    public void add(UUID billingAccountId, UUID productId, UUID productOfferId) {

        var billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);
        var product = catalogServiceClient.getProductId(productId);
        var basket = basketRepository.getBasketByBillingAccountId(billingAccount.getId());
        var productCampaign = catalogServiceClient.getCampaignProductId(product.getId());
        var productOffer = catalogServiceClient.getProductOffer(productOfferId,product.getId());

        if (basket == null) {
            basket = new Basket();
            basket.setBillingAccountId(billingAccount.getId());
            basket.setTotalPrice(BigDecimal.ZERO);
        }

        // Ürün sepette var mı kontrol et
        BasketItem existingItem = basket.getBasketItems().stream()
                .filter(item -> item.getProductId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Ürün zaten sepetteyse quantity artır
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            BigDecimal discountRate = existingItem.getDiscountRate() != null ? existingItem.getDiscountRate() : BigDecimal.ZERO;
            BigDecimal discountedPrice = existingItem.getPrice()
                    .multiply(BigDecimal.ONE.subtract(discountRate))
                    .multiply(BigDecimal.valueOf(existingItem.getQuantity()));
            existingItem.setDiscountedPrice(discountedPrice);
        } else {
            // Yeni ürün ekleniyorsa
            BasketItem basketItem = new BasketItem();
            basketItem.setProductId(product.getId());
            basketItem.setProductName(product.getName());
            basketItem.setPrice(product.getPrice());
            basketItem.setProductOfferId(productOffer.getId());
            basketItem.setCampaignProductId(productCampaign.getId());
            basketItem.setDiscountRate(productOffer.getDiscountRate() != null ? productOffer.getDiscountRate() : BigDecimal.ZERO);
            basketItem.setQuantity(1);
            basketItem.setDiscountedPrice(
                    product.getPrice().multiply(BigDecimal.ONE.subtract(basketItem.getDiscountRate()))
            );
            /*if(basket.getTotalPrice()==null){
                basket.setTotalPrice(basketItem.getPrice());
            }
            else {
                basket.setTotalPrice(basket.getTotalPrice().add(basketItem.getPrice()));
            }*/

            basket.getBasketItems().add(basketItem);
        }

        BigDecimal total = basket.getBasketItems().stream()
                .map(item -> item.getDiscountedPrice() != null ? item.getDiscountedPrice() : item.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        basket.setTotalPrice(total);

        basketRepository.add(basket);

        /*
        if(basket==null){
            basket = new Basket();
            basket.setBillingAccountId(billingAccount.getId());
        }

        BasketItem basketItem = new BasketItem();
        basketItem.setProductId(product.getId());
        basketItem.setProductName(product.getName());
        basketItem.setPrice(product.getPrice());
        basket.setBillingAccountId(billingAccount.getId());
        if(basket.getTotalPrice()==null){
            basket.setTotalPrice(basketItem.getPrice());
        }
        else {
            basket.setTotalPrice(basket.getTotalPrice().add(basketItem.getPrice()));
        }
        if(basket.getBasketItems()==null){}
        basket.getBasketItems().add(basketItem);*/

        basketRepository.add(basket);
    }

    @Override
    public Map<String, Basket> getAll() {
        return basketRepository.getBaskets();
    }

    @Override
    public void clearBasket(UUID billingAccountId) {
        basketRepository.clearBasket(billingAccountId);
    }

    @Override
    public void clearBasketItems(UUID billingAccountId) {
        basketRepository.clearBasketItems(billingAccountId);
    }

    @Override
    public Basket getByBillingAccountId(UUID billingAccountId) {
        return basketRepository.getBasketByBillingAccountId(billingAccountId);
    }
}