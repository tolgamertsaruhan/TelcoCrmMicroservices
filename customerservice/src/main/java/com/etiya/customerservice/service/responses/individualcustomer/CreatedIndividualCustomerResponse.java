package com.etiya.customerservice.service.responses.individualcustomer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedIndividualCustomerResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String nationalId;
    private String motherName;
    private String fatherName;
    private String gender;
}
