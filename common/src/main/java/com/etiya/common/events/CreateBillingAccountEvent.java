package com.etiya.common.events;

public record CreateBillingAccountEvent(
        String billingAccountId,
        String customerId,
        String addressId,
        String type,
        String status,
        String accountNumber,
        String accountName
)  {
}
