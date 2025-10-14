package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.CorporateCustomer;
import com.etiya.customerservice.service.requests.corporatecustomer.CreateCorporateCustomerRequest;
import com.etiya.customerservice.service.responses.corporatecustomer.CreatedCorporateCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CorporateCustomerMapper {
    CorporateCustomerMapper INSTANCE = Mappers.getMapper(CorporateCustomerMapper.class);

    CorporateCustomer corporateCustomerFromCreateRequest(CreateCorporateCustomerRequest request);
    CreatedCorporateCustomerResponse createdResponseFromCorporateCustomer(CorporateCustomer corporateCustomer);
}
