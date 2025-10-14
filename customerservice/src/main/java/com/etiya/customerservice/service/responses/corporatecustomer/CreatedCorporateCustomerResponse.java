package com.etiya.customerservice.service.responses.corporatecustomer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCorporateCustomerResponse {
    private int id;
    private String companyName;
    private String taxNumber;
    private String companyType;
    private String customerNumber;
}
