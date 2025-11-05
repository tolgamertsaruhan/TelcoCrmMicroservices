package com.etiya.basketservice.client;

import com.etiya.common.responses.CampaignProductResponse;
import com.etiya.common.responses.ProductOfferResponse;
import com.etiya.common.responses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "catalogservice")
public interface CatalogServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProductId(@PathVariable("id") UUID id);

    @GetMapping("/api/campaign-products/by-product/{productId}")
    CampaignProductResponse getCampaignProductId(@PathVariable("productId") UUID id);

    @GetMapping("/api/product-offers/get-product-offer-with-productId-control/{id}/{productId}")
    ProductOfferResponse getProductOffer(@PathVariable("id") UUID id, @PathVariable("productId") UUID productId);
}
