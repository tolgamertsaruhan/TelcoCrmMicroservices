package com.etiya.catalogservice.controller;


import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.requests.product.CreateProductRequestHoca;
import com.etiya.catalogservice.service.responses.product.CreatedProductResponseHoca;
import com.etiya.common.responses.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponseHoca add(@RequestBody CreateProductRequestHoca request){
        return productService.add(request);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getById(@PathVariable UUID id){
        return productService.getById(id);
    }
}
