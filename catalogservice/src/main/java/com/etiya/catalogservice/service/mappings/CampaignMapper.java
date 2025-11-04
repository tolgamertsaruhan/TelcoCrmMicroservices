package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.service.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.requests.campaign.UpdateCampaignRequest;
import com.etiya.catalogservice.service.responses.campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.GetCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.GetListCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.UpdatedCampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CampaignMapper {

    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    Campaign campaignFromCreateRequest(CreateCampaignRequest request);

    CreatedCampaignResponse createdResponseFromCampaign(Campaign campaign);

    Campaign campaignFromUpdateRequest(UpdateCampaignRequest request, @MappingTarget Campaign campaign);

    UpdatedCampaignResponse updatedResponseFromCampaign(Campaign campaign);

    GetCampaignResponse getCampaignResponseFromCampaign(Campaign campaign);

    List<GetListCampaignResponse> getListCampaignResponsesFromCampaigns(List<Campaign> campaigns);
}
