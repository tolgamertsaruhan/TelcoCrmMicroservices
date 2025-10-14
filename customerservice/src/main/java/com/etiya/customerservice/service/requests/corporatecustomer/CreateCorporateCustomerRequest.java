package com.etiya.customerservice.service.requests.corporatecustomer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
    private String companyName;
    private String taxNumber;
    private String companyType;
}
