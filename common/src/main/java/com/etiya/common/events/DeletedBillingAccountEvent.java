package com.etiya.common.events;

public record DeletedBillingAccountEvent (
        String billingAccountId,
        String customerId,
        String addressId,
        String type,
        String status,
        String accountNumber,
        String accountName,
        String deletedDate
){
}
