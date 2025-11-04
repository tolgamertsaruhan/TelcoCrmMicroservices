package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CatalogProductOfferService;
import com.etiya.catalogservice.service.requests.catalogProductOffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.requests.catalogProductOffer.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.responses.catalogProductOffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.GetCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.GetListCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.UpdatedCatalogProductOfferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/catalog-product-offers")
public class CatalogProductOfferController {

    private final CatalogProductOfferService catalogProductOfferService;

    public CatalogProductOfferController(CatalogProductOfferService catalogProductOfferService) {
        this.catalogProductOfferService = catalogProductOfferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCatalogProductOfferResponse add(@RequestBody CreateCatalogProductOfferRequest request) {
        return catalogProductOfferService.add(request);
    }

    @PutMapping
    public UpdatedCatalogProductOfferResponse update(@RequestBody UpdateCatalogProductOfferRequest request) {
        return catalogProductOfferService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        catalogProductOfferService.delete(id);
    }

    @GetMapping("/{id}")
    public GetCatalogProductOfferResponse getById(@PathVariable UUID id) {
        return catalogProductOfferService.getById(id);
    }

    @GetMapping
    public List<GetListCatalogProductOfferResponse> getAll() {
        return catalogProductOfferService.getAll();
    }

    @DeleteMapping("/soft-delete/{id}")
    public void softDelete(@PathVariable UUID id) {
        catalogProductOfferService.softDelete(id);
    }
}