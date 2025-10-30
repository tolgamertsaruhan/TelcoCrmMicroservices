package com.etiya.customerservice.service.requests.individualcustomer;

import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.CreateAddressRequestForFull;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;

import java.util.List;

public class CreateFullIndividualCustomerRequest {

    private CreateIndividualCustomerRequest createIndividualCustomerRequest;
    private List<CreateAddressRequestForFull> addressRequestList;

    private List<CreateContactMediumRequest> createContactMediumRequests;

    public CreateIndividualCustomerRequest getCreateIndividualCustomerRequest() {
        return createIndividualCustomerRequest;
    }

    public void setCreateIndividualCustomerRequest(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        this.createIndividualCustomerRequest = createIndividualCustomerRequest;
    }

    public List<CreateAddressRequestForFull> getAddressRequestList() {
        return addressRequestList;
    }

    public void setAddressRequestList(List<CreateAddressRequestForFull> addressRequestList) {
        this.addressRequestList = addressRequestList;
    }

    public List<CreateContactMediumRequest> getCreateContactMediumRequests() {
        return createContactMediumRequests;
    }

    public void setCreateContactMediumRequests(List<CreateContactMediumRequest> createContactMediumRequests) {
        this.createContactMediumRequests = createContactMediumRequests;
    }

    public CreateFullIndividualCustomerRequest() {
    }

    public CreateFullIndividualCustomerRequest(CreateIndividualCustomerRequest createIndividualCustomerRequest, List<CreateAddressRequestForFull> addressRequestList, List<CreateContactMediumRequest> createContactMediumRequests) {
        this.createIndividualCustomerRequest = createIndividualCustomerRequest;
        this.addressRequestList = addressRequestList;
        this.createContactMediumRequests = createContactMediumRequests;
    }
}
