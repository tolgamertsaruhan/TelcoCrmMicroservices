package com.etiya.customerservice.service.requests.billingaccount;

import com.etiya.customerservice.domain.enums.BillingAccountType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillingAccountRequest {


    @NotBlank(message = "Account name is required")
    @Size(min = 3,max = 100,message = "Account name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9şıüğöçŞİÜĞÖÇ -]+$", message = "Account name can only contain letters, numbers, spaces, and hyphens")
    private String accountName;

    @NotNull(message = "Account Type is required")
    private BillingAccountType type;

    @NotNull(message = "CustomerId cannot be null")
    @Positive(message = "CustomerId must be a positive")
    private int customerId;

    @NotNull(message = "AddressId cannot be null")
    @Positive(message = "AddressId must be a positive")
    private int addressId;



}
