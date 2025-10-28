package com.etiya.customerservice.service.responses.individualcustomer;

import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;

import java.util.List;

public class CreateFullIndividualCustomerResponse {

    private CreatedIndividualCustomerResponse customerResponse;
    private List<CreatedAddressResponse> addressResponse;

    private List<CreatedContactMediumResponse> createdContactMediumResponses;

    public CreatedIndividualCustomerResponse getCustomerResponse() {
        return customerResponse;
    }

    public void setCustomerResponse(CreatedIndividualCustomerResponse customerResponse) {
        this.customerResponse = customerResponse;
    }

    public List<CreatedAddressResponse> getAddressResponse() {
        return addressResponse;
    }

    public void setAddressResponse(List<CreatedAddressResponse> addressResponse) {
        this.addressResponse = addressResponse;
    }

    public List<CreatedContactMediumResponse> getCreatedContactMediumResponses() {
        return createdContactMediumResponses;
    }

    public void setCreatedContactMediumResponses(List<CreatedContactMediumResponse> createdContactMediumResponses) {
        this.createdContactMediumResponses = createdContactMediumResponses;
    }


    public CreateFullIndividualCustomerResponse() {
    }

    public CreateFullIndividualCustomerResponse(CreatedIndividualCustomerResponse customerResponse, List<CreatedAddressResponse> addressResponse, List<CreatedContactMediumResponse> createdContactMediumResponses) {
        this.customerResponse = customerResponse;
        this.addressResponse = addressResponse;
        this.createdContactMediumResponses = createdContactMediumResponses;
    }
}
