package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import com.etiya.catalogservice.service.requests.catalogProductOffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.requests.catalogProductOffer.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.responses.catalogProductOffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.GetCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.GetListCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.UpdatedCatalogProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface CatalogProductOfferMapper {

    CatalogProductOfferMapper mapper = Mappers.getMapper(CatalogProductOfferMapper.class);

    // Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "catalog.id", source = "catalogId")
    @Mapping(target = "productOffer.id", source = "productOfferId")
    CatalogProductOffer createCatalogProductOfferRequestToCatalogProductOffer(CreateCatalogProductOfferRequest request);

    @Mapping(target = "catalog.id", source = "catalogId")
    @Mapping(target = "productOffer.id", source = "productOfferId")
    CatalogProductOffer updateCatalogProductOfferRequestToCatalogProductOffer(UpdateCatalogProductOfferRequest request);

    // Entity → Response
    @Mapping(target = "catalogId", source = "catalog.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    CreatedCatalogProductOfferResponse catalogProductOfferToCreatedCatalogProductOfferResponse(CatalogProductOffer catalogProductOffer);

    @Mapping(target = "catalogId", source = "catalog.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    UpdatedCatalogProductOfferResponse catalogProductOfferToUpdatedCatalogProductOfferResponse(CatalogProductOffer catalogProductOffer);

    @Mapping(target = "catalogId", source = "catalog.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    GetCatalogProductOfferResponse catalogProductOfferToGetCatalogProductOfferResponse(CatalogProductOffer catalogProductOffer);

    @Mapping(target = "catalogId", source = "catalog.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    GetListCatalogProductOfferResponse catalogProductOfferToGetListCatalogProductOfferResponse(CatalogProductOffer catalogProductOffer);

    List<GetListCatalogProductOfferResponse> catalogProductOfferListToGetListCatalogProductOfferResponseList(List<CatalogProductOffer> catalogProductOffers);
}