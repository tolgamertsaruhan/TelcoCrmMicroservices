package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.requests.characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.responses.characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.GetCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.UpdatedCharacteristicResponse;

import java.util.List;
import java.util.UUID;

public interface CharacteristicService {
    CreatedCharacteristicResponse add(CreateCharacteristicRequest request);

    UpdatedCharacteristicResponse update(UpdateCharacteristicRequest request);

    List<GetListCharacteristicResponse> getAll();

    GetCharacteristicResponse getById(UUID id);

    void delete(UUID id);

    void softDelete(UUID id);
}
