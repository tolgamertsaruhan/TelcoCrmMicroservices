package com.etiya.customerservice.service.responses.billingaccount;


import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;

import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class GetListBillingAccountResponse {
    private UUID id;
    private BillingAccountType type;
    private String accountName;
    private String accountNumber;
    private BillingAccountStatus status;
    private UUID customerId;
    private UUID addressId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BillingAccountType getType() {
        return type;
    }

    public void setType(BillingAccountType type) {
        this.type = type;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BillingAccountStatus getStatus() {
        return status;
    }

    public void setStatus(BillingAccountStatus status) {
        this.status = status;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public GetListBillingAccountResponse() {
    }

    public GetListBillingAccountResponse(UUID id, BillingAccountType type, String accountName, String accountNumber, BillingAccountStatus status, UUID customerId, UUID addressId) {
        this.id = id;
        this.type = type;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.status = status;
        this.customerId = customerId;
        this.addressId = addressId;
    }
}
