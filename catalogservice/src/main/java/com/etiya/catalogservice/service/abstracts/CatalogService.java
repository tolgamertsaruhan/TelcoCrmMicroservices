package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.requests.catalog.UpdateCatalogRequest;
import com.etiya.catalogservice.service.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.GetCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.UpdatedCatalogResponse;

import java.util.List;
import java.util.UUID;

public interface CatalogService {
    CreatedCatalogResponse add(CreateCatalogRequest request);
    UpdatedCatalogResponse update(UpdateCatalogRequest request);
    void delete(UUID id);
    GetCatalogResponse getById(UUID id);
    List<GetListCatalogResponse> getAll();
    void softDelete(UUID id);
}
