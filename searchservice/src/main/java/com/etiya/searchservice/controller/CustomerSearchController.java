package com.etiya.searchservice.controller;

import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerSearchRepository;
import com.etiya.searchservice.service.CustomerSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    /*@GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> search(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String customerNumber,
            @RequestParam(required = false) String nationalId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) String type
    ) {

        CustomerSearch request = new CustomerSearch();
        request.setId(id);
        request.setCustomerNumber(customerNumber);
        request.setNationalId(nationalId);
        request.setFirstName(firstName);
        request.setLastName(lastName);

        if (value != null && !value.isBlank()) {
            ContactMedium contactMedium = new ContactMedium();
            contactMedium.setValue(value);
            // Eğer type gelmediyse PHONE olarak varsay
            if (type == null || type.isBlank()) {
                contactMedium.setType("PHONE");
            } else {
                contactMedium.setType(type.toUpperCase()); // "phone" → "PHONE"
            }

            List<ContactMedium> contactList = new ArrayList<>();
            contactList.add(contactMedium);
            request.setContactMediumSearchList(contactList);
        }



        return customerSearchService.searchDynamic(request);
    }*/

    @GetMapping("search")
    public List<CustomerSearch> search(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String accountNumber,
            @RequestParam(required = false) String nationalId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String middleName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String value,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return customerSearchService.searchDynamic(id, accountNumber, nationalId, firstName, middleName, lastName, value, page, size);
    }
}
