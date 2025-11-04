package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.requests.campaign.UpdateCampaignRequest;
import com.etiya.catalogservice.service.responses.campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.GetCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.GetListCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.UpdatedCampaignResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public CreatedCampaignResponse add(@RequestBody CreateCampaignRequest request) {
        return campaignService.add(request);
    }

    @PutMapping
    public UpdatedCampaignResponse update(@RequestBody UpdateCampaignRequest request) {
        return campaignService.update(request);
    }

    @GetMapping
    public List<GetListCampaignResponse> getAll() {
        return campaignService.getAll();
    }

    @GetMapping("/{id}")
    public GetCampaignResponse getById(@PathVariable UUID id) {
        return campaignService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        campaignService.delete(id);
    }

    @DeleteMapping("/{id}/soft-delete")
    public void softDelete(@PathVariable UUID id) {
        campaignService.softDelete(id);
    }
}
