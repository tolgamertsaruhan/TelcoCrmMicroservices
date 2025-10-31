package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerSearchRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static io.micrometer.common.util.StringUtils.isNotBlank;

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
    public void addAddress(Address address) {

        var cs = customerSearchRepository.findById(address.getCustomerId()).orElseThrow();
        cs.getAddressSearchList().removeIf(a -> Objects.equals(a.getAddressId(), address.getAddressId())); // idempotent

         cs.getAddressSearchList().add(address);
         customerSearchRepository.save(cs);


    }

    @Override
    public void updateAddress(Address address) {
        var cs = customerSearchRepository.findById(address.getCustomerId()).orElseThrow();
        cs.getAddressSearchList().removeIf(a -> Objects.equals(a.getAddressId(), address.getAddressId())); // idempotent
        cs.getAddressSearchList().add(address);
        customerSearchRepository.save(cs);
    }

    @Override
    public void deleteAddress(Address address) {

        var cs = customerSearchRepository.findById(address.getCustomerId()).orElseThrow();
        cs.getAddressSearchList().removeIf(a -> Objects.equals(a.getAddressId(), address.getAddressId()));
        customerSearchRepository.save(cs);
    }

    @Override
    public List<CustomerSearch> searchAllFields(String keyword) {
        return customerSearchRepository.searchAllFields(keyword);
    }

    @Override
    public List<CustomerSearch> firstNameMatch(String firstName) {
        return customerSearchRepository.firstNameMatch(firstName);
    }

    @Override
    public List<CustomerSearch> nationalIdTerm(String keyword) {
        return customerSearchRepository.nationalIdTerm(keyword);
    }

    @Override
    public List<CustomerSearch> fuzzyTerm(String keyword) {
        return customerSearchRepository.fuzzyTerm(keyword);
    }

    @Override
    public List<CustomerSearch> filterSurnameWithCitiesBool(String firstName, String cityName) {
        return customerSearchRepository.filterSurnameWithCitiesBool(firstName, cityName);
    }

    @Override
    public List<CustomerSearch> findByBirthYearRange(String startYear, String endYear) {
        return  customerSearchRepository.findByBirthYearRange(startYear, endYear);
    }

    @Override
    public void addContactMedium(ContactMedium contactMedium) {
        var cs = customerSearchRepository.findById(contactMedium.getCustomerId()).orElseThrow();
        cs.getContactMediumSearchList().removeIf(a -> Objects.equals(a.getContactMediumId(), contactMedium.getContactMediumId())); // idempotent

        cs.getContactMediumSearchList().add(contactMedium);
        customerSearchRepository.save(cs);
    }

    @Override
    public void updateContactMedium(ContactMedium contactMedium) {
        var cs = customerSearchRepository.findById(contactMedium.getCustomerId()).orElseThrow();
        cs.getContactMediumSearchList().removeIf(a -> Objects.equals(a.getContactMediumId(), contactMedium.getContactMediumId()));
        cs.getContactMediumSearchList().add(contactMedium);
        customerSearchRepository.save(cs);
    }

    @Override
    public void deleteContactMedium(ContactMedium contactMedium) {
        var cs = customerSearchRepository.findById(contactMedium.getCustomerId()).orElseThrow();
        cs.getContactMediumSearchList().removeIf(a -> Objects.equals(a.getContactMediumId(), contactMedium.getContactMediumId()));
        customerSearchRepository.save(cs);
    }

    /*@Override
    public List<CustomerSearch> searchDynamic(CustomerSearch request) {
         return customerSearchRepository.searchDynamic(request);
    }*/

    @Override
    public List<CustomerSearch> searchDynamic(String id, String customerNumber, String nationalId, String firstName, String middleName, String lastName, String value,int page, int size) {
        return customerSearchRepository.searchDynamic(id,customerNumber,nationalId,firstName,middleName,lastName,value,page, size);
    }

    @Override
    public void updateCustomer(CustomerSearch customerSearch) {
        var cs = customerSearchRepository.findById(customerSearch.getId()).orElseThrow();
        cs.setId(customerSearch.getId());
        cs.setFirstName(customerSearch.getFirstName());
        cs.setMiddleName(customerSearch.getMiddleName());
        cs.setLastName(customerSearch.getLastName());
        cs.setDateOfBirth(customerSearch.getDateOfBirth());
        cs.setGender(customerSearch.getGender());
        cs.setNationalId(customerSearch.getNationalId());
        cs.setMotherName(customerSearch.getMotherName());
        cs.setFatherName(customerSearch.getFatherName());

        customerSearchRepository.save(cs);
    }
}
