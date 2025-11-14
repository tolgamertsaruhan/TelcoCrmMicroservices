package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.campaignProductOffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.requests.campaignProductOffer.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.responses.campaignProductOffer.CreatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.GetCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.GetListCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.UpdatedCampaignProductOfferResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;

import java.util.List;
import java.util.UUID;

public interface CampaignProductOfferService {
    CreatedCampaignProductOfferResponse add(CreateCampaignProductOfferRequest request);

    UpdatedCampaignProductOfferResponse update(UpdateCampaignProductOfferRequest request);

    List<GetListCampaignProductOfferResponse> getAll();

    GetCampaignProductOfferResponse getById(UUID id);

    void delete(UUID id);

    void softDelete(UUID id);

    GetCampaignProductOfferResponse findCampaignsByProductOfferId(UUID productOfferId);

    GetCampaignProductOfferResponse findCampaignProductOfferById(UUID campaignProductOfferId);

    List<GetCampaignProductOfferResponse> getCampaignProductOffersByCampaignId(UUID campaignId);

    List<GetListCampaignProductOfferResponse> searchByCampaignIdAndProductOfferName(UUID campaignId, String productOfferName);
}
