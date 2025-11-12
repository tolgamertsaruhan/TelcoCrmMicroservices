package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.ProdCharValue;
import com.etiya.catalogservice.service.requests.prodCharValue.CreateProdCharValueRequest;
import com.etiya.catalogservice.service.requests.prodCharValue.UpdateProdCharValueRequest;
import com.etiya.catalogservice.service.responses.prodCharValue.CreatedProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.GetListProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.GetProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.UpdatedProdCharValueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProdCharValueMapper {

    ProdCharValueMapper INSTANCE = Mappers.getMapper(ProdCharValueMapper.class);

    @Mapping(source = "charValueId", target = "charValue.id")
    @Mapping(source = "productOfferId", target = "productOffer.id")
    ProdCharValue prodCharValueFromCreateRequest(CreateProdCharValueRequest request);

    @Mapping(target = "charValueId", source = "charValue.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    CreatedProdCharValueResponse createdResponseFromProdCharValue(ProdCharValue prodCharValue);

    @Mapping(source = "charValueId", target = "charValue.id")
    @Mapping(source = "productOfferId", target = "productOffer.id")
    ProdCharValue prodCharValueFromUpdateRequest(UpdateProdCharValueRequest request, @MappingTarget ProdCharValue prodCharValue);

    @Mapping(target = "charValueId", source = "charValue.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    UpdatedProdCharValueResponse updatedResponseFromProdCharValue(ProdCharValue prodCharValue);

    @Mapping(target = "charValueId", source = "charValue.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    GetProdCharValueResponse getProdCharValueResponseFromProdCharValue(ProdCharValue prodCharValue);

    @Mapping(target = "charValueId", source = "charValue.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    List<GetListProdCharValueResponse> getListProdCharValueResponsesFromProdCharValues(List<ProdCharValue> prodCharValues);
}