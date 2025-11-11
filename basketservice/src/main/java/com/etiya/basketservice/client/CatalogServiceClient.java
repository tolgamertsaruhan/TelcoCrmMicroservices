package com.etiya.basketservice.client;

import com.etiya.common.responses.CampaignProductOfferResponse;
import com.etiya.common.responses.CampaignResponse;
import com.etiya.common.responses.ProductOfferResponse;
import com.etiya.common.responses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "catalogservice")
public interface CatalogServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProductId(@PathVariable("id") UUID id);

//    @GetMapping("/api/campaign-product-offers/by-product-offer/{productOfferId}")
//    CampaignProductOfferResponse getCampaignProductOfferId(@PathVariable("productOfferId") UUID id);

    @GetMapping("/api/product-offers/{id}")
    ProductOfferResponse getProductOffer(@PathVariable("id") UUID id);

    @GetMapping("api/campaign-product-offers/get-campaign-product-offer-by-id/{campaignProductOfferId}")
    CampaignProductOfferResponse getCampaignProductOffer (@PathVariable("campaignProductOfferId") UUID campaignProductOfferId);

    @GetMapping("api/campaigns/{id}")
    CampaignResponse getCampaignId(@PathVariable("id") UUID id);

    @GetMapping("/api/campaign-product-offers/by-campaign/{campaignId}")
    List<CampaignProductOfferResponse> getAllCampaignProductOffersByCampaignId(@PathVariable("campaignId") UUID campaignId);
}
