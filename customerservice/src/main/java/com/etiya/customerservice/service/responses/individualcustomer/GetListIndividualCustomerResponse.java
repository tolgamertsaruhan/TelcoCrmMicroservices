package com.etiya.customerservice.service.responses.individualcustomer;


import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetListIndividualCustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String nationalId;
    private String motherName;
    private String fatherName;
    private String gender;
    private LocalDate dateOfBirth;

    private List<GetListAddressResponse> addresses;

}
