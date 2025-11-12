package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CustomerOffer;
import com.etiya.catalogservice.service.requests.customerOffer.CreateCustomerOfferRequest;
import com.etiya.catalogservice.service.requests.customerOffer.UpdateCustomerOfferRequest;
import com.etiya.catalogservice.service.responses.customerOffer.CreatedCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.GetCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.GetListCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.UpdatedCustomerOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerOfferMapper {

    CustomerOfferMapper INSTANCE = Mappers.getMapper(CustomerOfferMapper.class);

    @Mapping(source = "productOfferId", target = "productOffer.id")
    CustomerOffer customerOfferFromCreateRequest(CreateCustomerOfferRequest request);

    @Mapping(target = "productOfferId", source = "productOffer.id")
    CreatedCustomerOfferResponse createdResponseFromCustomerOffer(CustomerOffer customerOffer);

    @Mapping(source = "productOfferId", target = "productOffer.id")
    CustomerOffer customerOfferFromUpdateRequest(UpdateCustomerOfferRequest request, @MappingTarget CustomerOffer customerOffer);

    @Mapping(target = "productOfferId", source = "productOffer.id")
    UpdatedCustomerOfferResponse updatedResponseFromCustomerOffer(CustomerOffer customerOffer);

    @Mapping(target = "productOfferId", source = "productOffer.id")
    GetCustomerOfferResponse getCustomerOfferResponseFromCustomerOffer(CustomerOffer customerOffer);

    @Mapping(target = "productOfferId", source = "productOffer.id")
    List<GetListCustomerOfferResponse> getListCustomerOfferResponsesFromCustomerOffers(List<CustomerOffer> customerOffers);
}