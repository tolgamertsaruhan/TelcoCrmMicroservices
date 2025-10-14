package com.etiya.customerservice.service.abstracts;



import com.etiya.customerservice.service.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomer.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetListIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    //void add(IndividualCustomer individualCustomer);
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    List<GetListIndividualCustomerResponse> getList();
    //List<GetListIndividualCustomerResponse> findAllWithAddresses();

    GetIndividualCustomerResponse getByLastName(String lastName);
    List<GetListIndividualCustomerResponse> getByFirstNameStartingWith(String prefix);
    List<GetListIndividualCustomerResponse> getByCustomerNumberPattern(String pattern);


}
