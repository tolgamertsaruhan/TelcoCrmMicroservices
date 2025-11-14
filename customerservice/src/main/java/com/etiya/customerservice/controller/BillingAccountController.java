package com.etiya.customerservice.controller;


import com.etiya.common.responses.BillingAccountResponse;
import com.etiya.customerservice.domain.entities.BillingAccount;
import com.etiya.customerservice.service.abstracts.BillingAccountService;
import com.etiya.customerservice.service.requests.billingaccount.CreateBillingAccountRequest;
import com.etiya.customerservice.service.requests.billingaccount.UpdateBillingAccountRequest;
import com.etiya.customerservice.service.responses.address.GetAddressResponse;
import com.etiya.customerservice.service.responses.billingaccount.CreatedBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingaccount.GetListBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingaccount.UpdatedBillingAccountResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/billingAccounts")
public class BillingAccountController {

    private final BillingAccountService billingAccountService;

    public BillingAccountController(BillingAccountService billingAccountService) {
        this.billingAccountService = billingAccountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBillingAccountResponse add(@Valid @RequestBody CreateBillingAccountRequest createBillingAccountRequest) {
        return billingAccountService.add(createBillingAccountRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedBillingAccountResponse update(@RequestBody UpdateBillingAccountRequest updateBillingAccountRequest) {
        return billingAccountService.update(updateBillingAccountRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListBillingAccountResponse> get() {
        return billingAccountService.getList();
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable UUID id){
        billingAccountService.softDelete(id);
    }

    @DeleteMapping("{id}")//pathvariable ile anlaşsın diye, mapping yapsın diye
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        billingAccountService.delete(id);
    }

    @GetMapping("/by-customer-bill/{customerId}")
    public ResponseEntity<List<GetListBillingAccountResponse>> getByCustomerId(@PathVariable String customerId) {
        List<GetListBillingAccountResponse> responses = billingAccountService.getByCustomerId(customerId);
        return ResponseEntity.ok(responses);
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BillingAccountResponse getById(@PathVariable UUID id){
        return billingAccountService.getById(id);
    }

    @GetMapping("/{billingAccountId}/customer-id")
    @ResponseStatus(HttpStatus.OK)
    public UUID getCustomerByBillingAccountId(@PathVariable UUID billingAccountId) {
        return billingAccountService.getCustomerIdByBillingAccountId(billingAccountId);
    }
}
