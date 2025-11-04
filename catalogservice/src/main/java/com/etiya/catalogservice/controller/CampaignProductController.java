package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignProductService;
import com.etiya.catalogservice.service.requests.campaignProduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.requests.campaignProduct.UpdateCampaignProductRequest;
import com.etiya.catalogservice.service.responses.campaignProduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.GetCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.GetListCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.UpdatedCampaignProductResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campaign-products")
public class CampaignProductController {

    private final CampaignProductService campaignProductService;

    public CampaignProductController(CampaignProductService campaignProductService) {
        this.campaignProductService = campaignProductService;
    }

    @PostMapping
    public CreatedCampaignProductResponse add(@RequestBody CreateCampaignProductRequest request) {
        return campaignProductService.add(request);
    }

    @PutMapping
    public UpdatedCampaignProductResponse update(@RequestBody UpdateCampaignProductRequest request) {
        return campaignProductService.update(request);
    }

    @GetMapping
    public List<GetListCampaignProductResponse> getAll() {
        return campaignProductService.getAll();
    }

    @GetMapping("/{id}")
    public GetCampaignProductResponse getById(@PathVariable UUID id) {
        return campaignProductService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        campaignProductService.delete(id);
    }

    @DeleteMapping("/{id}/soft-delete")
    public void softDelete(@PathVariable UUID id) {
        campaignProductService.softDelete(id);
    }
}