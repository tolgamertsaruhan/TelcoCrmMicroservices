package com.etiya.common.events;

public record DeletedCustomerEvent (String customerId, String customerNumber, String firstName, String middleName, String lastName, String nationalId, String motherName, String fatherName, String gender, String dateOfBirth,String deletedDate){
}
