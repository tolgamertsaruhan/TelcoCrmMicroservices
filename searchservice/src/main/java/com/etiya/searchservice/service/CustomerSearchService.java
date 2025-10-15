package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.CustomerSearch;

import java.util.List;

public interface CustomerSearchService {
    void add(CustomerSearch customerSearch);
    List<CustomerSearch> findAll();
    void delete(String id);
}
