package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;

import java.util.List;

public interface CustomerSearchService {
    void add(CustomerSearch customerSearch);
    List<CustomerSearch> findAll();
    void delete(String id);

    // Address ops
    void addAddress(String customerId, Address address);
    void updateAddress(String customerId, Address address);
    void deleteAddress(String customerId, String addressId);

    List<CustomerSearch> searchAllFields(String keyword);
    List<CustomerSearch> firstNameMatch(String firstName);
    List<CustomerSearch> nationalIdTerm(String keyword);
    List<CustomerSearch> fuzzyTerm(String keyword);

    // ContactMedium ops
    //void addContactMedium(String customerId, ContactMedium contact);
    //void updateContactMedium(String customerId, ContactMedium contact);
    //void deleteContactMedium(String customerId, int contactId);
}
