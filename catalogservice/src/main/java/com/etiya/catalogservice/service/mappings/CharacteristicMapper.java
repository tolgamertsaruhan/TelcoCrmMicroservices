package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.service.requests.characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.requests.characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.responses.characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.GetCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.UpdatedCharacteristicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CharacteristicMapper {

    CharacteristicMapper INSTANCE = Mappers.getMapper(CharacteristicMapper.class);

    Characteristic characteristicFromCreateRequest(CreateCharacteristicRequest request);

    CreatedCharacteristicResponse createdResponseFromCharacteristic(Characteristic characteristic);

    Characteristic characteristicFromUpdateRequest(UpdateCharacteristicRequest request, @MappingTarget Characteristic characteristic);

    UpdatedCharacteristicResponse updatedResponseFromCharacteristic(Characteristic characteristic);

    GetCharacteristicResponse getCharacteristicResponseFromCharacteristic(Characteristic characteristic);

    List<GetListCharacteristicResponse> getListCharacteristicResponsesFromCharacteristics(List<Characteristic> characteristics);
}