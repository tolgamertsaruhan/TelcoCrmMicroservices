package com.etiya.customerservice.service.requests.individualcustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UpdateIndividualCustomerRequest {
    private UUID id;
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

    private String dateOfBirth;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UpdateIndividualCustomerRequest() {
    }

    public UpdateIndividualCustomerRequest(String firstName, String lastName, String middleName, String nationalId, String motherName, String fatherName, String gender, String dateOfBirth, UUID id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nationalId = nationalId;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
