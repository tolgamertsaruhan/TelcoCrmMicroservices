package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignProductOfferService;
import com.etiya.catalogservice.service.requests.campaignProductOffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.requests.campaignProductOffer.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.responses.campaignProductOffer.CreatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.GetCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.GetListCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.UpdatedCampaignProductOfferResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campaign-product-offers")
public class CampaignProductOfferController {

    private final CampaignProductOfferService campaignProductOfferService;

    public CampaignProductOfferController(CampaignProductOfferService campaignProductOfferService) {
        this.campaignProductOfferService = campaignProductOfferService;
    }

    @PostMapping
    public CreatedCampaignProductOfferResponse add(@RequestBody CreateCampaignProductOfferRequest request) {
        return campaignProductOfferService.add(request);
    }

    @PutMapping
    public UpdatedCampaignProductOfferResponse update(@RequestBody UpdateCampaignProductOfferRequest request) {
        return campaignProductOfferService.update(request);
    }

    @GetMapping
    public List<GetListCampaignProductOfferResponse> getAll() {
        return campaignProductOfferService.getAll();
    }

    @GetMapping("/{id}")
    public GetCampaignProductOfferResponse getById(@PathVariable UUID id) {
        return campaignProductOfferService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        campaignProductOfferService.delete(id);
    }

    @DeleteMapping("/{id}/soft-delete")
    public void softDelete(@PathVariable UUID id) {
        campaignProductOfferService.softDelete(id);
    }

    @GetMapping("/by-product-offer/{productOfferId}")
    public GetCampaignProductOfferResponse getCampaignsByProductOfferId(@PathVariable UUID productOfferId) {
        return campaignProductOfferService.findCampaignsByProductOfferId(productOfferId);
    }

    @GetMapping("/get-campaign-product-offer-by-id/{campaignProductOfferId}")
    public GetCampaignProductOfferResponse getCampaignProductOfferById(@PathVariable UUID campaignProductOfferId) {
        return campaignProductOfferService.findCampaignProductOfferById(campaignProductOfferId);
    }

    @GetMapping("/by-campaign/{campaignId}")
    public List<GetCampaignProductOfferResponse> getCampaignProductOffersByCampaignId(@PathVariable UUID campaignId) {
        return campaignProductOfferService.getCampaignProductOffersByCampaignId(campaignId);
    }
}