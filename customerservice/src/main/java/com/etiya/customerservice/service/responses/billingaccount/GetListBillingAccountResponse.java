package com.etiya.customerservice.service.responses.billingaccount;


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
public class GetListBillingAccountResponse {
    private int id;
    private BillingAccountType type;
    private String accountName;
    private String accountNumber;
    private BillingAccountStatus status;
    private int customerId;
    private int addressId;
}
