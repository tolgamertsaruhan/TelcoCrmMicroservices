package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.requests.campaign.UpdateCampaignRequest;
import com.etiya.catalogservice.service.responses.campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.GetCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.GetListCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.UpdatedCampaignResponse;

import java.util.List;
import java.util.UUID;

public interface CampaignService {
    CreatedCampaignResponse add(CreateCampaignRequest request);

    UpdatedCampaignResponse update(UpdateCampaignRequest request);

    List<GetListCampaignResponse> getAll();

    GetCampaignResponse getById(UUID id);

    void delete(UUID id);

    void softDelete(UUID id);
}
