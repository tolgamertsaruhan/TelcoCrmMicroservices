package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomer.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import com.etiya.customerservice.service.responses.individualcustomer.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetListIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.UpdatedIndividualCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AddressMapper.class, ContactMediumMapper.class})

public interface IndividualCustomerMapper {
    IndividualCustomerMapper INSTANCE = Mappers.getMapper(IndividualCustomerMapper.class); //BURDA İNSTANCE AYAĞA KALDIRILIYOR
    //Arka planda bir instance çalışıyor.

    //requestten gelen veriyi individual customere verecğiz
    //individual customerdan requeste gidiyorum.
    IndividualCustomer individualCustomerFromCreateIndividualCustomerRequest(CreateIndividualCustomerRequest createIndividualCustomerRequest);

    //responsedan individual customere
    CreatedIndividualCustomerResponse createdIndividualCustomerResponseFromIndividualCustomer(IndividualCustomer individualCustomer);

    List<GetListIndividualCustomerResponse> getListIndividualCustomerResponseFromIndividualCustomers(List<IndividualCustomer> individualCustomers);

    GetIndividualCustomerResponse getIndividualCustomerResponseFromIndividualCustomers(IndividualCustomer individualCustomers);

    IndividualCustomer individualCustomerFromUpdateIndividualCustomerRequest(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, @MappingTarget IndividualCustomer individualCustomer);

    UpdatedIndividualCustomerResponse updatedIndividualCustomerResponseFromIndividualCustomer(IndividualCustomer individualCustomer);
}
