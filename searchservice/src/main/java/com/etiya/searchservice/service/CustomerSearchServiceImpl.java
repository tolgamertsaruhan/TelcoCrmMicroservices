package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerSearchServiceImpl implements CustomerSearchService {
    private final CustomerSearchRepository customerSearchRepository;

    public CustomerSearchServiceImpl(CustomerSearchRepository customerSearchRepository) {
        this.customerSearchRepository = customerSearchRepository;
    }

    public void add(CustomerSearch customerSearch){
        customerSearchRepository.save(customerSearch);
    }

    @Override
    public List<CustomerSearch> findAll() {
        return StreamSupport.stream(customerSearchRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(String id){
        //CustomerSearch customerSearch = customerSearchRepository.findById(id).get();
        customerSearchRepository.deleteById(id);
    }

    @Override
    public void addAddress(String customerId, Address address) {

        var cs = customerSearchRepository.findById(customerId).orElseThrow();
        cs.getAddressSearchList().removeIf(a -> Objects.equals(a.getAddressId(), address.getAddressId())); // idempotent

         cs.getAddressSearchList().add(address);
         customerSearchRepository.save(cs);


    }

    @Override
    public void updateAddress(String customerId, Address address) {
        var cs = customerSearchRepository.findById(customerId).orElseThrow();
        cs.getAddressSearchList().removeIf(a -> Objects.equals(a.getAddressId(), address.getAddressId())); // idempotent
        cs.getAddressSearchList().add(address);
        customerSearchRepository.save(cs);
    }

    @Override
    public void deleteAddress(String customerId, String addressId) {

        var cs = customerSearchRepository.findById(customerId).orElseThrow();
        cs.getAddressSearchList().removeIf(a -> Objects.equals(a.getAddressId(), addressId));
        customerSearchRepository.save(cs);
    }
}
