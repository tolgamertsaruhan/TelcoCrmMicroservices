package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.requests.product.CreateProductRequestHoca;
import com.etiya.catalogservice.service.requests.product.UpdateProductRequest;
import com.etiya.catalogservice.service.responses.product.*;
import com.etiya.common.responses.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    CreatedProductResponseHoca add(CreateProductRequestHoca request);
    ProductResponse getById(UUID id);

    CreatedProductResponse add(CreateProductRequest request);
    UpdatedProductResponse update(UpdateProductRequest request);
    List<GetListProductResponse> getAll();
    GetProductResponse getProductById(UUID id);
    void delete(UUID id);  // Hard delete
    void softDelete(UUID id);

//    List<GetListProductResponse> getByCatalogId(UUID catalogId);
//    List<GetListProductResponse> getBySpecificationId(UUID specificationId);
}
