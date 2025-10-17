package com.etiya.searchservice.controller;

import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-search/")
public class CustomerSearchController {
    private final CustomerSearchService customerSearchService;

    public CustomerSearchController(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> findAll() {
        return customerSearchService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        customerSearchService.delete(id);
    }

    @GetMapping("fulltext")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> search(@RequestParam String keyword) {
        return customerSearchService.searchAllFields(keyword);
    }

    @GetMapping("firstName/match")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> matchFirstName(@RequestParam String firstName) {
        return customerSearchService.firstNameMatch(firstName);
    }

    @GetMapping("findNationalId")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> findNationalId(@RequestParam String keyword) {
        return customerSearchService.nationalIdTerm(keyword);
    }

    @GetMapping("familierName/fuzzy")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> fuzzyTerm(@RequestParam String keyword) {
        return customerSearchService.fuzzyTerm(keyword);
    }

    @GetMapping("filterSurnameWithCities")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> filterSurnameWithCities(@RequestParam String firstName, @RequestParam String cityName) {
        return customerSearchService.filterSurnameWithCitiesBool(firstName, cityName);
    }

    @GetMapping("findByBirthYearRange")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> findByBirthYearRange(@RequestParam String startYear,  @RequestParam String endYear) {
        return customerSearchService.findByBirthYearRange(startYear, endYear);
    }
}
