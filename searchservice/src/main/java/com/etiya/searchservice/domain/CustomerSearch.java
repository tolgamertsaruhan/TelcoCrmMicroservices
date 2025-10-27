package com.etiya.searchservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "customer-search")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CustomerSearch {
    @Id
    private String id;
    private String customerNumber;
    //@Field(type = FieldType.Keyword)
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalId;
    private String motherName;
    private String fatherName;
    private String gender;
    private String dateOfBirth;

    @Field(type = FieldType.Nested)
    List<Address> addressSearchList = new ArrayList<>();

    @Field(type = FieldType.Nested)
    List<ContactMedium> contactMediumSearchList = new ArrayList<>();

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<ContactMedium> getContactMediumSearchList() {
        return contactMediumSearchList;
    }

    public void setContactMediumSearchList(List<ContactMedium> contactMediumSearchList) {
        this.contactMediumSearchList = contactMediumSearchList;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getAddressSearchList() {
        return addressSearchList;
    }

    public void setAddressSearchList(List<Address> addressSearchList) {
        this.addressSearchList = addressSearchList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
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

    public CustomerSearch() {
    }

    public CustomerSearch(String id, String customerNumber, String firstName, String middleName, String lastName, String nationalId, String motherName, String fatherName, String gender, String dateOfBirth) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
}
