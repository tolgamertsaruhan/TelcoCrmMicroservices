package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CustomerOfferService;
import com.etiya.catalogservice.service.requests.customerOffer.CreateCustomerOfferRequest;
import com.etiya.catalogservice.service.requests.customerOffer.UpdateCustomerOfferRequest;
import com.etiya.catalogservice.service.responses.customerOffer.CreatedCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.GetCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.GetListCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.UpdatedCustomerOfferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer-offers")
public class CustomerOfferController {

    private final CustomerOfferService customerOfferService;

    public CustomerOfferController(CustomerOfferService customerOfferService) {
        this.customerOfferService = customerOfferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCustomerOfferResponse add(@RequestBody CreateCustomerOfferRequest request) {
        return customerOfferService.add(request);
    }

    @PutMapping
    public UpdatedCustomerOfferResponse update(@RequestBody UpdateCustomerOfferRequest request) {
        return customerOfferService.update(request);
    }

    @GetMapping
    public List<GetListCustomerOfferResponse> getAll() {
        return customerOfferService.getAll();
    }

    @GetMapping("/{id}")
    public GetCustomerOfferResponse getById(@PathVariable UUID id) {
        return customerOfferService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        customerOfferService.delete(id);
    }

    @DeleteMapping("/{id}/soft-delete")
    public void softDelete(@PathVariable UUID id) {
        customerOfferService.softDelete(id);
    }
}