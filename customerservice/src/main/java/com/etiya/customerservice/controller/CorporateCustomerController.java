package com.etiya.customerservice.controller;



import com.etiya.customerservice.service.abstracts.CorporateCustomerService;
import com.etiya.customerservice.service.requests.corporatecustomer.CreateCorporateCustomerRequest;
import com.etiya.customerservice.service.responses.corporatecustomer.CreatedCorporateCustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/corporate-customers")
public class CorporateCustomerController {

    private final CorporateCustomerService corporateCustomerService;

    public CorporateCustomerController(CorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCorporateCustomerResponse add(@RequestBody CreateCorporateCustomerRequest request) {
        return corporateCustomerService.add(request);
    }
}