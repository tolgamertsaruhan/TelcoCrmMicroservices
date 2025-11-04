package com.etiya.basketservice.client;

import com.etiya.basketservice.configuration.FeignClientInterceptorConfig;
import com.etiya.common.responses.BillingAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customerservice", configuration = FeignClientInterceptorConfig.class)
public interface CustomerServiceClient {

    @GetMapping("/api/billingAccounts/{id}")
    BillingAccountResponse getBillingAccountById(@PathVariable("id") UUID id);
}