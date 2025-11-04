package com.etiya.catalogservice.controller;


import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.requests.product.CreateProductRequestHoca;
import com.etiya.catalogservice.service.requests.product.UpdateProductRequest;
import com.etiya.catalogservice.service.responses.product.*;
import com.etiya.common.responses.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("HocaAdd")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponseHoca add(@RequestBody CreateProductRequestHoca request){
        return productService.add(request);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getById(@PathVariable UUID id){
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreatedProductResponse> add(@RequestBody CreateProductRequest request) {
        CreatedProductResponse response = productService.add(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UpdatedProductResponse> update(@RequestBody UpdateProductRequest request) {
        UpdatedProductResponse response = productService.update(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> softDelete(@PathVariable UUID id) {
        productService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("product-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetProductResponse> getProductById(@PathVariable UUID id) {
        GetProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetListProductResponse>> getAll() {
        List<GetListProductResponse> responses = productService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/by-catalog/{catalogId}")
    public ResponseEntity<List<GetListProductResponse>> getByCatalogId(@PathVariable UUID catalogId) {
        return ResponseEntity.ok(productService.getByCatalogId(catalogId));
    }

    @GetMapping("/by-specification/{specificationId}")
    public ResponseEntity<List<GetListProductResponse>> getBySpecificationId(@PathVariable UUID specificationId) {
        return ResponseEntity.ok(productService.getBySpecificationId(specificationId));
    }
}
