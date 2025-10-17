package com.etiya.customerservice.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "individual_customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class IndividualCustomer extends Customer {

    @Column(name = "firstName",nullable = false,length = 50)
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "nationalId",unique = true,updatable = false,length = 11)
    private String nationalId;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "motherName")
    private String motherName;

    @Column(name = "fatherName")
    private String fatherName;

    @Column(name = "gender")
    private String gender;

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public IndividualCustomer(String firstName, String lastName, String middleName, String nationalId, String dateOfBirth, String motherName, String fatherName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
    }

    public IndividualCustomer(UUID id, String customerNumber, List<Address> addresses, String firstName, String lastName, String middleName, String nationalId, String dateOfBirth, String motherName, String fatherName, String gender) {
        super(id, customerNumber, addresses);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
    }

    public IndividualCustomer() {
    }

    public IndividualCustomer(UUID id, String customerNumber, List<Address> addresses) {
        super(id, customerNumber, addresses);
    }
}