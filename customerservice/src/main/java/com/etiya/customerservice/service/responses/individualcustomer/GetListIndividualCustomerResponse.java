package com.etiya.customerservice.service.responses.individualcustomer;


import com.etiya.customerservice.service.responses.address.GetListAddressResponse;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class GetListIndividualCustomerResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String nationalId;
    private String motherName;
    private String fatherName;
    private String gender;
    private LocalDate dateOfBirth;

    private List<GetListAddressResponse> addresses;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<GetListAddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<GetListAddressResponse> addresses) {
        this.addresses = addresses;
    }

    public GetListIndividualCustomerResponse() {
    }

    public GetListIndividualCustomerResponse(UUID id, String firstName, String lastName, String middleName, String nationalId, String motherName, String fatherName, String gender, LocalDate dateOfBirth, List<GetListAddressResponse> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nationalId = nationalId;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.addresses = addresses;
    }
}
