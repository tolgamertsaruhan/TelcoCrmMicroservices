package com.etiya.searchservice.domain;

public class BillingAccount {

    private String billingAccountId;
    private String type;
    private String status;

    private String accountNumber;

    private String accountName;

    private String customerId;

    private String addressId;

    private String deletedDate;

    public String getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(String billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    public BillingAccount() {
    }

    public BillingAccount(String billingAccountId, String type, String status, String accountNumber, String accountName, String customerId, String addressId, String deletedDate) {
        this.billingAccountId = billingAccountId;
        this.type = type;
        this.status = status;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.customerId = customerId;
        this.addressId = addressId;
        this.deletedDate = deletedDate;
    }

    public BillingAccount(String billingAccountId, String type, String status, String accountNumber, String accountName, String customerId, String addressId) {
        this.billingAccountId = billingAccountId;
        this.type = type;
        this.status = status;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.customerId = customerId;
        this.addressId = addressId;
    }

}
