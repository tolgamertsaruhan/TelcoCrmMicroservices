package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.productSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.requests.productSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.responses.productSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.UpdatedProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.GetProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.GetListProductSpecificationResponse;

import java.util.List;
import java.util.UUID;

public interface ProductSpecificationService {

    CreatedProductSpecificationResponse add(CreateProductSpecificationRequest request);

    UpdatedProductSpecificationResponse update(UpdateProductSpecificationRequest request);

    void delete(UUID id);

    void softDelete(UUID id);

    GetProductSpecificationResponse getById(UUID id);

    List<GetListProductSpecificationResponse> getAll();
}
