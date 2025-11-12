package com.etiya.salesservice.client;

import com.etiya.salesservice.service.responses.BasketResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "basketservice")
public interface BasketServiceClient {

    @GetMapping("/api/baskets/{billingAccountId}")
    BasketResponse getByBillingAccountId(@PathVariable UUID billingAccountId);


}