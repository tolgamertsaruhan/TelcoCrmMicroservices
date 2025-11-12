package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.charValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.requests.charValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.responses.charValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.GetCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.UpdatedCharValueResponse;

import java.util.List;
import java.util.UUID;

public interface CharValueService {
    CreatedCharValueResponse add(CreateCharValueRequest request);

    UpdatedCharValueResponse update(UpdateCharValueRequest request);

    List<GetListCharValueResponse> getAll();

    GetCharValueResponse getById(UUID id);

    void delete(UUID id);

    void softDelete(UUID id);
}
