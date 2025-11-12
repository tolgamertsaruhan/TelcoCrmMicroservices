package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.service.requests.charValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.requests.charValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.responses.charValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.GetCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.UpdatedCharValueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CharValueMapper {

    CharValueMapper INSTANCE = Mappers.getMapper(CharValueMapper.class);

    @Mapping(source = "characteristicId", target = "characteristic.id")
    CharValue charValueFromCreateRequest(CreateCharValueRequest request);

    @Mapping(target = "characteristicId", source = "characteristic.id")
    CreatedCharValueResponse createdResponseFromCharValue(CharValue charValue);

    @Mapping(source = "characteristicId", target = "characteristic.id")
    CharValue charValueFromUpdateRequest(UpdateCharValueRequest request, @MappingTarget CharValue charValue);

    @Mapping(target = "characteristicId", source = "characteristic.id")
    UpdatedCharValueResponse updatedResponseFromCharValue(CharValue charValue);

    @Mapping(target = "characteristicId", source = "characteristic.id")
    GetCharValueResponse getCharValueResponseFromCharValue(CharValue charValue);

    @Mapping(target = "characteristicId", source = "characteristic.id")
    List<GetListCharValueResponse> getListCharValueResponsesFromCharValues(List<CharValue> charValues);
}
