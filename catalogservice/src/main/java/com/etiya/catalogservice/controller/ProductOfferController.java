package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.requests.productOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.requests.productOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.responses.productOffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.GetListProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.GetProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.UpdatedProductOfferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-offers/")
public class ProductOfferController {

    private final ProductOfferService productOfferService;

    public ProductOfferController(ProductOfferService productOfferService) {
        this.productOfferService = productOfferService;
    }

    @PostMapping
    public ResponseEntity<CreatedProductOfferResponse> createProductOffer(@RequestBody CreateProductOfferRequest request) {
        CreatedProductOfferResponse response = productOfferService.addProductOffer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UpdatedProductOfferResponse> updateProductOffer(@RequestBody UpdateProductOfferRequest request) {
        UpdatedProductOfferResponse response = productOfferService.update(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<GetProductOfferResponse> getProductOffer(@PathVariable UUID id) {
        GetProductOfferResponse response = productOfferService.getProductOffer(id);
        return response != null ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetListProductOfferResponse>> getAllProductOffers() {
        List<GetListProductOfferResponse> response = productOfferService.getAllProductOffers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Soft delete işlemi
    @DeleteMapping("{id}/soft")
    public ResponseEntity<Void> softDelete(@PathVariable UUID id) {
        productOfferService.softDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Hard delete işlemi
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productOfferService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}