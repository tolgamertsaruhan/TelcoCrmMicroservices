package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.service.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomer.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetListIndividualCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AddressMapper.class})

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
}
