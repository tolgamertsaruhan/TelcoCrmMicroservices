package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProdCharValueService;
import com.etiya.catalogservice.service.requests.prodCharValue.CreateProdCharValueRequest;
import com.etiya.catalogservice.service.requests.prodCharValue.UpdateProdCharValueRequest;
import com.etiya.catalogservice.service.responses.prodCharValue.CreatedProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.GetListProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.GetProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.UpdatedProdCharValueResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/prod-char-values")
public class ProdCharValueController {

    private final ProdCharValueService prodCharValueService;

    public ProdCharValueController(ProdCharValueService prodCharValueService) {
        this.prodCharValueService = prodCharValueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProdCharValueResponse add(@RequestBody CreateProdCharValueRequest request) {
        return prodCharValueService.add(request);
    }

    @PutMapping
    public UpdatedProdCharValueResponse update(@RequestBody UpdateProdCharValueRequest request) {
        return prodCharValueService.update(request);
    }

    @GetMapping
    public List<GetListProdCharValueResponse> getAll() {
        return prodCharValueService.getAll();
    }

    @GetMapping("/{id}")
    public GetProdCharValueResponse getById(@PathVariable UUID id) {
        return prodCharValueService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        prodCharValueService.delete(id);
    }

    @DeleteMapping("/{id}/soft-delete")
    public void softDelete(@PathVariable UUID id) {
        prodCharValueService.softDelete(id);
    }
}