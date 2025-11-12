package com.etiya.catalogservice.service.concretes;


import com.etiya.catalogservice.domain.entities.CustomerOffer;
import com.etiya.catalogservice.repository.CustomerOfferRepository;
import com.etiya.catalogservice.service.abstracts.CustomerOfferService;
import com.etiya.catalogservice.service.mappings.CustomerOfferMapper;
import com.etiya.catalogservice.service.requests.customerOffer.CreateCustomerOfferRequest;
import com.etiya.catalogservice.service.requests.customerOffer.UpdateCustomerOfferRequest;
import com.etiya.catalogservice.service.responses.customerOffer.CreatedCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.GetCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.GetListCustomerOfferResponse;
import com.etiya.catalogservice.service.responses.customerOffer.UpdatedCustomerOfferResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerOfferServiceImpl implements CustomerOfferService {

    private final CustomerOfferRepository customerOfferRepository;

    public CustomerOfferServiceImpl(CustomerOfferRepository customerOfferRepository) {
        this.customerOfferRepository = customerOfferRepository;
    }

    @Override
    public CreatedCustomerOfferResponse add(CreateCustomerOfferRequest request) {
        CustomerOffer customerOffer = CustomerOfferMapper.INSTANCE.customerOfferFromCreateRequest(request);
        CustomerOffer created = customerOfferRepository.save(customerOffer);
        return CustomerOfferMapper.INSTANCE.createdResponseFromCustomerOffer(created);
    }

    @Override
    public UpdatedCustomerOfferResponse update(UpdateCustomerOfferRequest request) {
        CustomerOffer existing = customerOfferRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("CustomerOffer not found"));

        CustomerOffer updatedCustomerOffer = CustomerOfferMapper.INSTANCE.customerOfferFromUpdateRequest(request, existing);
        CustomerOffer updated = customerOfferRepository.save(updatedCustomerOffer);
        return CustomerOfferMapper.INSTANCE.updatedResponseFromCustomerOffer(updated);
    }

    @Override
    public List<GetListCustomerOfferResponse> getAll() {
        List<CustomerOffer> customerOffers = customerOfferRepository.findAll();
        return CustomerOfferMapper.INSTANCE.getListCustomerOfferResponsesFromCustomerOffers(customerOffers);
    }

    @Override
    public GetCustomerOfferResponse getById(UUID id) {
        CustomerOffer customerOffer = customerOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerOffer not found"));
        return CustomerOfferMapper.INSTANCE.getCustomerOfferResponseFromCustomerOffer(customerOffer);
    }

    @Override
    public void delete(UUID id) {
        CustomerOffer customerOffer = customerOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerOffer not found"));
        customerOfferRepository.delete(customerOffer);
    }

    @Override
    public void softDelete(UUID id) {
        CustomerOffer customerOffer = customerOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerOffer not found"));
        customerOffer.setDeletedDate(LocalDateTime.now());
        customerOfferRepository.save(customerOffer);
    }
}