package com.etiya.customerservice.controller;


import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.requests.individualcustomer.CreateFullIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomer.CreateFullIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetListIndividualCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController //api olduğu için
@RequestMapping("/api/individual-customers") //bununla ilgili giden requesti neye maplediğimizi bildiriyoruz
public class IndividualCustomerController {
    private final IndividualCustomerService individualCustomerService;

    public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest individualCustomer) {
        return individualCustomerService.add(individualCustomer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListIndividualCustomerResponse> getList(){
        return individualCustomerService.getList();
    }
/*
    @GetMapping("getListWithAddresses")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListIndividualCustomerResponse> getListWithAddresses(){
        return individualCustomerService.findAllWithAddresses();
    }*/

    @GetMapping("{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerResponse getByLastName(@PathVariable String lastName) {
        return individualCustomerService.getByLastName(lastName);
    }

    @GetMapping("getByFirstNameStartingWith/{prefix}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListIndividualCustomerResponse> getByFirstNameStartingWith(@PathVariable String prefix){
        return individualCustomerService.getByFirstNameStartingWith(prefix);
    }

    @GetMapping("getByCustomerNumberPattern/{pattern}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListIndividualCustomerResponse> getByCustomerNumberPattern(@PathVariable String pattern){
        return individualCustomerService.getByCustomerNumberPattern(pattern);
    }

    @PostMapping("/create-full")
    public ResponseEntity<CreateFullIndividualCustomerResponse> createFullCustomer(
            @RequestBody CreateFullIndividualCustomerRequest request) {
        CreateFullIndividualCustomerResponse response = individualCustomerService.createFullIndividualCustomer(request);
        return ResponseEntity.ok(response);
    }


    // full customer get id için controller
    /*
    @GetMapping("/get-full/{id}")
    public ResponseEntity<CreateFullIndividualCustomerResponse> getFullCustomer(@PathVariable UUID id) {
        CreateFullIndividualCustomerResponse response = individualCustomerService.getFullIndividualCustomer(id);
        return ResponseEntity.ok(response);
    }*/
}
