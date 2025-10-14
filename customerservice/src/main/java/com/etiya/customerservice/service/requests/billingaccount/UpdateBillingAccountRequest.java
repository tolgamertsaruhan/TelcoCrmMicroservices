package com.etiya.customerservice.service.requests.billingaccount;


import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBillingAccountRequest {
    private int id;
    private String accountName;
    private int customerId;
    private int addressId;
    private BillingAccountType type;
    private BillingAccountStatus status;

}