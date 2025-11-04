package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CampaignProduct;
import com.etiya.catalogservice.service.requests.campaignProduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.requests.campaignProduct.UpdateCampaignProductRequest;
import com.etiya.catalogservice.service.responses.campaignProduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.GetCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.GetListCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.UpdatedCampaignProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CampaignProductMapper {

    CampaignProductMapper INSTANCE = Mappers.getMapper(CampaignProductMapper.class);

    @Mapping(source = "campaignId", target = "campaign.id")
    @Mapping(source = "productId", target = "product.id")
    CampaignProduct campaignProductFromCreateRequest(CreateCampaignProductRequest request);

    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "productId", source = "product.id")
    CreatedCampaignProductResponse createdResponseFromCampaignProduct(CampaignProduct campaignProduct);

    @Mapping(source = "campaignId", target = "campaign.id")
    @Mapping(source = "productId", target = "product.id")
    //@Mapping(target = "campaign", ignore = true)
    //@Mapping(target = "product", ignore = true)
    CampaignProduct campaignProductFromUpdateRequest(UpdateCampaignProductRequest request, @MappingTarget CampaignProduct campaignProduct);


    // update de hata aldı manuel yap dedi gpt
    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "productId", source = "product.id")
    UpdatedCampaignProductResponse updatedResponseFromCampaignProduct(CampaignProduct campaignProduct);

    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "productId", source = "product.id")
    GetCampaignProductResponse getCampaignProductResponseFromEntity(CampaignProduct campaignProduct);

    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "productId", source = "product.id")
    List<GetListCampaignProductResponse> getListResponsesFromEntities(List<CampaignProduct> campaignProducts);
}
