package com.etiya.customerservice.service.abstracts;



import com.etiya.customerservice.service.requests.individualcustomer.CreateFullIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomer.CreateFullIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetListIndividualCustomerResponse;

import java.util.List;
import java.util.UUID;

public interface IndividualCustomerService {
    //void add(IndividualCustomer individualCustomer);
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    List<GetListIndividualCustomerResponse> getList();
    //List<GetListIndividualCustomerResponse> findAllWithAddresses();

    GetIndividualCustomerResponse getByLastName(String lastName);
    List<GetListIndividualCustomerResponse> getByFirstNameStartingWith(String prefix);
    List<GetListIndividualCustomerResponse> getByCustomerNumberPattern(String pattern);

    CreateFullIndividualCustomerResponse createFullIndividualCustomer(CreateFullIndividualCustomerRequest request);

    //CreateFullIndividualCustomerResponse getFullIndividualCustomer(UUID id);


}
