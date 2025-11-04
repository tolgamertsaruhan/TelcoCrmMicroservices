package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.product.CreateProductRequestHoca;
import com.etiya.catalogservice.service.responses.product.CreatedProductResponseHoca;
import com.etiya.common.responses.ProductResponse;

import java.util.UUID;

public interface ProductService {
    CreatedProductResponseHoca add(CreateProductRequestHoca request);
    ProductResponse getById(UUID id);
}
