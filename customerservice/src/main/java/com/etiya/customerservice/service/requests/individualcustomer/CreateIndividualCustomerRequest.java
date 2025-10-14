package com.etiya.customerservice.service.requests.individualcustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    @NotBlank(message = "Identity number is required")
    @Size(min = 11, max = 11, message = "Identity number must be 11 characters")
    @Pattern(regexp = "^[0-9]+$",message = "Identity number must contain only numbers")
    private String nationalId;
    private String motherName;
    private String fatherName;
    private String gender;

}
