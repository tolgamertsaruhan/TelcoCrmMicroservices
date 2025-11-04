package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.requests.catalog.UpdateCatalogRequest;
import com.etiya.catalogservice.service.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.GetCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.UpdatedCatalogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCatalogResponse add(@RequestBody CreateCatalogRequest request) {
        return catalogService.add(request);
    }

    @PutMapping
    public UpdatedCatalogResponse update(@RequestBody UpdateCatalogRequest request) {
        return catalogService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        catalogService.delete(id);
    }

    @GetMapping("/{id}")
    public GetCatalogResponse getById(@PathVariable UUID id) {
        return catalogService.getById(id);
    }

    @GetMapping
    public List<GetListCatalogResponse> getAll() {
        return catalogService.getAll();
    }

    @DeleteMapping("/soft-delete/{id}")
    public void softDelete(@PathVariable UUID id) {
        catalogService.softDelete(id);
    }
}