package com.etiya.customerservice.service.abstracts;


import com.etiya.customerservice.service.requests.corporatecustomer.CreateCorporateCustomerRequest;
import com.etiya.customerservice.service.responses.corporatecustomer.CreatedCorporateCustomerResponse;

public interface CorporateCustomerService {
    CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest request);
}
