package com.etiya.customerservice.service.requests.billingaccount;


import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBillingAccountRequest {
    private UUID id;
    private String accountName;
    private UUID customerId;
    private UUID addressId;
    private BillingAccountType type;
    private BillingAccountStatus status;

}