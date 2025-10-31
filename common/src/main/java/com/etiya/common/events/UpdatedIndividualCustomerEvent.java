
package com.etiya.common.events;

public record UpdatedIndividualCustomerEvent(String customerId, String nationalId, String dateOfBirth,
                                             String fatherName, String motherName, String firstName,
                                             String middleName, String lastName, String gender) {
        }