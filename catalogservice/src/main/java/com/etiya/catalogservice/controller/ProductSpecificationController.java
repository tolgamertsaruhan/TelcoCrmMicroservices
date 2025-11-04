package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.requests.productSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.requests.productSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.responses.productSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.GetProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.UpdatedProductSpecificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-specifications/")
public class ProductSpecificationController {

    private final ProductSpecificationService productSpecificationService;

    public ProductSpecificationController(ProductSpecificationService productSpecificationService) {
        this.productSpecificationService = productSpecificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreatedProductSpecificationResponse> add(@RequestBody CreateProductSpecificationRequest request) {
        CreatedProductSpecificationResponse response = productSpecificationService.add(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UpdatedProductSpecificationResponse> update(@RequestBody UpdateProductSpecificationRequest request) {
        UpdatedProductSpecificationResponse response = productSpecificationService.update(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productSpecificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> softDelete(@PathVariable UUID id) {
        productSpecificationService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetProductSpecificationResponse> getById(@PathVariable UUID id) {
        GetProductSpecificationResponse response = productSpecificationService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetListProductSpecificationResponse>> getAll() {
        List<GetListProductSpecificationResponse> responses = productSpecificationService.getAll();
        return ResponseEntity.ok(responses);
    }
}
