package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.campaignProduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.requests.campaignProduct.UpdateCampaignProductRequest;
import com.etiya.catalogservice.service.responses.campaignProduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.GetCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.GetListCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.UpdatedCampaignProductResponse;

import java.util.List;
import java.util.UUID;

public interface CampaignProductService {
    CreatedCampaignProductResponse add(CreateCampaignProductRequest request);

    UpdatedCampaignProductResponse update(UpdateCampaignProductRequest request);

    List<GetListCampaignProductResponse> getAll();

    GetCampaignProductResponse getById(UUID id);

    void delete(UUID id);

    void softDelete(UUID id);
}
