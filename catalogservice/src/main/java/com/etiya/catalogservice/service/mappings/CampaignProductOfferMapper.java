package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import com.etiya.catalogservice.service.requests.campaignProductOffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.requests.campaignProductOffer.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.responses.campaignProductOffer.CreatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.GetCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.GetListCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.UpdatedCampaignProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CampaignProductOfferMapper {

    CampaignProductOfferMapper INSTANCE = Mappers.getMapper(CampaignProductOfferMapper.class);

    @Mapping(source = "campaignId", target = "campaign.id")
    @Mapping(source = "productOfferId", target = "productOffer.id")
    CampaignProductOffer campaignProductFromCreateRequest(CreateCampaignProductOfferRequest request);

    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    CreatedCampaignProductOfferResponse createdResponseFromCampaignProduct(CampaignProductOffer campaignProductOffer);

    @Mapping(source = "campaignId", target = "campaign.id")
    @Mapping(source = "productOfferId", target = "productOffer.id")
    //@Mapping(target = "campaign", ignore = true)
    //@Mapping(target = "product", ignore = true)
    CampaignProductOffer campaignProductFromUpdateRequest(UpdateCampaignProductOfferRequest request, @MappingTarget CampaignProductOffer campaignProductOffer);


    // update de hata aldı manuel yap dedi gpt
    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    UpdatedCampaignProductOfferResponse updatedResponseFromCampaignProduct(CampaignProductOffer campaignProductOffer);

    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    GetCampaignProductOfferResponse getCampaignProductResponseFromEntity(CampaignProductOffer campaignProductOffer);

    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    List<GetListCampaignProductOfferResponse> getListResponsesFromEntities(List<CampaignProductOffer> campaignProductOffers);
}
