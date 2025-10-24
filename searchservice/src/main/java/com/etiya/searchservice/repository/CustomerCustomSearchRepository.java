package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CustomerCustomSearchRepository {

    public List<CustomerSearch> searchDynamic(CustomerSearch request);
}
