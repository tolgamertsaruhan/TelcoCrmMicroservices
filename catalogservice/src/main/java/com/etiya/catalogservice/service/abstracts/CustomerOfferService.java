package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.customerOffer.CreateCustomerOfferRequest;
import com.etiya.catalogservice.service.requests.customerOffer.UpdateCustomerOfferRequest;
import com.etiya.catalogservice.service.responses.customerOffer.CreatedCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.GetCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.GetListCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.UpdatedCustomerOfferResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerOfferService {
    CreatedCustomerOfferResponse add(CreateCustomerOfferRequest request);

    UpdatedCustomerOfferResponse update(UpdateCustomerOfferRequest request);

    List<GetListCustomerOfferResponse> getAll();

    GetCustomerOfferResponse getById(UUID id);

    void delete(UUID id);

    void softDelete(UUID id);
}