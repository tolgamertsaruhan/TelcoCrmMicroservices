package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.product.CreateProductRequestHoca;
import com.etiya.catalogservice.service.responses.product.CreatedProductResponseHoca;

public interface ProductService {
    CreatedProductResponseHoca add(CreateProductRequestHoca request);
}
