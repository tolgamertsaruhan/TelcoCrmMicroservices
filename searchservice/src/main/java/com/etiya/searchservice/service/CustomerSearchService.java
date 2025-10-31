package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;

import java.util.List;

public interface CustomerSearchService {
    void add(CustomerSearch customerSearch);
    List<CustomerSearch> findAll();
    void delete(String id);

    // Address ops
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(Address address);

    List<CustomerSearch> searchAllFields(String keyword);
    List<CustomerSearch> firstNameMatch(String firstName);
    List<CustomerSearch> nationalIdTerm(String keyword);
    List<CustomerSearch> fuzzyTerm(String keyword);
    List<CustomerSearch> filterSurnameWithCitiesBool(String firstName, String cityName);
    List<CustomerSearch> findByBirthYearRange(String startYear, String endYear);

    void addContactMedium(ContactMedium contactMedium);
    void updateContactMedium(ContactMedium contactMedium);
    void deleteContactMedium(ContactMedium contactMedium);

    //public List<CustomerSearch> searchDynamic(CustomerSearch request);
    List<CustomerSearch> searchDynamic(
            String id,
            String customerNumber,
            String nationalId,
            String firstName,
            String middleName,
            String lastName,
            String value,
            int page, int size
    );

    void updateCustomer(CustomerSearch customerSearch);
}
