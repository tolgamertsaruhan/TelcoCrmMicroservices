package com.etiya.customerservice.service.concretes;


import com.etiya.customerservice.domain.entities.BillingAccount;
import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.repository.BillingAccountRepository;
import com.etiya.customerservice.service.abstracts.BillingAccountService;
import com.etiya.customerservice.service.mappings.BillingAccountMapper;
import com.etiya.customerservice.service.requests.billingaccount.CreateBillingAccountRequest;
import com.etiya.customerservice.service.requests.billingaccount.UpdateBillingAccountRequest;
import com.etiya.customerservice.service.responses.billingaccount.CreatedBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingaccount.GetListBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingaccount.UpdatedBillingAccountResponse;
import com.etiya.customerservice.service.rules.BillingAccountBusinessRules;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class BillingAccountServiceImpl implements BillingAccountService {
    private final BillingAccountRepository billingAccountRepository;
    private final BillingAccountBusinessRules billingAccountBusinessRules;

    public BillingAccountServiceImpl(BillingAccountRepository billingAccountRepository, BillingAccountBusinessRules billingAccountBusinessRules) {
        this.billingAccountRepository = billingAccountRepository;
        this.billingAccountBusinessRules = billingAccountBusinessRules;
    }
    @Override
    public CreatedBillingAccountResponse add(CreateBillingAccountRequest request) {
        billingAccountBusinessRules.checkIfCustomerHasAddress(request.getCustomerId());
        billingAccountBusinessRules.checkIfAddressBelongsToCustomer(request.getAddressId(), request.getCustomerId());
        billingAccountBusinessRules.checkIfCustomerTypeMatchesAccountType(
                request.getCustomerId(),
                request.getType()
        );

        BillingAccount billingAccount = BillingAccountMapper.INSTANCE
                .billingAccountFromCreateBillingAccountRequest(request);

        billingAccount.setStatus(BillingAccountStatus.ACTIVE);

        BillingAccount created = billingAccountRepository.save(billingAccount);

        return BillingAccountMapper.INSTANCE
                .createdBillingAccountResponseFromBillingAccount(created);
    }

    @Override
    public UpdatedBillingAccountResponse update(UpdateBillingAccountRequest request) {
        BillingAccount billingAccount = billingAccountRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Billing Account not found"));

        billingAccountBusinessRules.checkIfTypeCanBeChanged(request.getId(), request.getType());

        if (billingAccount.getAddress().getId() != request.getAddressId()) {
            billingAccountBusinessRules.checkIfAddressBelongsToCustomer(
                    request.getAddressId(),
                    request.getCustomerId()
            );
        }

        if (request.getStatus() != null && request.getStatus() != billingAccount.getStatus()) {
            billingAccountBusinessRules.checkIfStatusTransitionIsValid(
                    request.getId(),
                    request.getStatus()
            );
        }

        BillingAccountMapper.INSTANCE.updateBillingAccountFromRequest(request, billingAccount);

        BillingAccount updated = billingAccountRepository.save(billingAccount);

        return BillingAccountMapper.INSTANCE
                .updatedBillingAccountResponseFromBillingAccount(updated);
    }

    @Override
    public List<GetListBillingAccountResponse> getList() {
        return billingAccountRepository.findAll().stream().map( billingAccount -> {
            GetListBillingAccountResponse response = new GetListBillingAccountResponse();
            response.setId(billingAccount.getId());
            response.setAccountName(billingAccount.getAccountName());
            response.setCustomerId(billingAccount.getCustomer().getId());
            response.setAddressId(billingAccount.getAddress().getId());
            response.setAccountNumber(billingAccount.getAccountNumber());
            response.setStatus(billingAccount.getStatus());
            response.setType(billingAccount.getType());
            return response;
    }).toList();
    }

    @Override
    public void delete(UUID id) {
        billingAccountBusinessRules.checkIfBillingAccountCanBeDeleted(id);
        BillingAccount billingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Billing Account not found"));
        billingAccountRepository.delete(billingAccount);
    }

    @Override
    public void softDelete(UUID id) {
        BillingAccount billingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Billing Account not found"));
        billingAccount.setDeletedDate(LocalDateTime.now());
        billingAccountRepository.save(billingAccount);
    }
}
