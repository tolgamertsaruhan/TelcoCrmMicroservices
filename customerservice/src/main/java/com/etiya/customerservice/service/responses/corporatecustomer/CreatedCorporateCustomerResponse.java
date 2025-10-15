package com.etiya.customerservice.service.responses.corporatecustomer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCorporateCustomerResponse {
    private UUID id;
    private String companyName;
    private String taxNumber;
    private String companyType;
    private String customerNumber;
}
