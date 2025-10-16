package com.etiya.customerservice.service.responses.individualcustomer;



import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class CreatedIndividualCustomerResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String nationalId;
    private String motherName;
    private String fatherName;
    private String gender;

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

    public CreatedIndividualCustomerResponse() {
    }

    public CreatedIndividualCustomerResponse(UUID id, String firstName, String lastName, String middleName, String nationalId, String motherName, String fatherName, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nationalId = nationalId;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
    }
}
