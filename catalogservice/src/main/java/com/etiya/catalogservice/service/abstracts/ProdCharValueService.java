package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.prodCharValue.CreateProdCharValueRequest;
import com.etiya.catalogservice.service.requests.prodCharValue.UpdateProdCharValueRequest;
import com.etiya.catalogservice.service.responses.prodCharValue.CreatedProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.GetListProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.GetProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.UpdatedProdCharValueResponse;

import java.util.List;
import java.util.UUID;

public interface ProdCharValueService {
    CreatedProdCharValueResponse add(CreateProdCharValueRequest request);

    UpdatedProdCharValueResponse update(UpdateProdCharValueRequest request);

    List<GetListProdCharValueResponse> getAll();

    GetProdCharValueResponse getById(UUID id);

    void delete(UUID id);

    void softDelete(UUID id);
}