package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductSpecCharacteristicService;
import com.etiya.catalogservice.service.requests.productSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.requests.productSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.responses.productSpecCharacteristic.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-spec-characteristics/")
public class ProductSpecCharacteristicController {

    private final ProductSpecCharacteristicService service;

    public ProductSpecCharacteristicController(ProductSpecCharacteristicService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductSpecCharacteristicResponse add(@RequestBody CreateProductSpecCharacteristicRequest request) {
        return service.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductSpecCharacteristicResponse update(@RequestBody UpdateProductSpecCharacteristicRequest request) {
        return service.update(request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable UUID id) {
        service.softDelete(id);
    }

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListProductSpecCharacteristicResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductSpecCharacteristicResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("spec/{specId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListProductSpecCharacteristicResponse> getByProductSpecificationId(@PathVariable UUID specId) {
        return service.getByProductSpecificationId(specId);
    }

    @GetMapping("characteristic/{characteristicId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListProductSpecCharacteristicResponse> getByCharacteristicId(@PathVariable UUID characteristicId) {
        return service.getByCharacteristicId(characteristicId);
    }
}
