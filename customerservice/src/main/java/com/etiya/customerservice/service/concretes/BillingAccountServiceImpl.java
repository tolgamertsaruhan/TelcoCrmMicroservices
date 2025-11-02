package com.etiya.customerservice.service.concretes;


import com.etiya.common.events.*;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.BillingAccount;
import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.repository.BillingAccountRepository;
import com.etiya.customerservice.service.abstracts.BillingAccountService;
import com.etiya.customerservice.service.mappings.BillingAccountMapper;
import com.etiya.customerservice.service.requests.billingaccount.CreateBillingAccountRequest;
import com.etiya.customerservice.service.requests.billingaccount.UpdateBillingAccountRequest;
import com.etiya.customerservice.service.responses.address.GetAddressResponse;
import com.etiya.customerservice.service.responses.billingaccount.CreatedBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingaccount.GetListBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingaccount.UpdatedBillingAccountResponse;
import com.etiya.customerservice.service.rules.BillingAccountBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.billingAccount.CreateBillingAccountProducer;
import com.etiya.customerservice.transport.kafka.producer.billingAccount.DeletedBillingAccountProducer;
import com.etiya.customerservice.transport.kafka.producer.billingAccount.UpdatedBillingAccountProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BillingAccountServiceImpl implements BillingAccountService {
    private final BillingAccountRepository billingAccountRepository;
    private final BillingAccountBusinessRules billingAccountBusinessRules;

    private final CreateBillingAccountProducer createBillingAccountProducer;

    private final UpdatedBillingAccountProducer updatedBillingAccountProducer;

    private final DeletedBillingAccountProducer deletedBillingAccountProducer;

    private final AddressRepository addressRepository;

    public BillingAccountServiceImpl(BillingAccountRepository billingAccountRepository, BillingAccountBusinessRules billingAccountBusinessRules, CreateBillingAccountProducer createBillingAccountProducer, UpdatedBillingAccountProducer updatedBillingAccountProducer, DeletedBillingAccountProducer deletedBillingAccountProducer, AddressRepository addressRepository) {
        this.billingAccountRepository = billingAccountRepository;
        this.billingAccountBusinessRules = billingAccountBusinessRules;
        this.createBillingAccountProducer = createBillingAccountProducer;
        this.updatedBillingAccountProducer = updatedBillingAccountProducer;
        this.deletedBillingAccountProducer = deletedBillingAccountProducer;
        this.addressRepository = addressRepository;
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

        CreateBillingAccountEvent event =
                new CreateBillingAccountEvent(created.getId().toString(),
                        created.getCustomer().getId().toString(),
                        created.getAddress().getId().toString(),
                        created.getType().toString(),
                        created.getStatus().toString(),
                        created.getAccountNumber(),
                        created.getAccountName());

        createBillingAccountProducer.produceBillingAccountCreated(event);

        return BillingAccountMapper.INSTANCE
                .createdBillingAccountResponseFromBillingAccount(created);
    }

    @Override
    public List<GetListBillingAccountResponse> getByCustomerId(String customerId) {

        try {
            UUID uuid = UUID.fromString(customerId);
            List<BillingAccount> billingAccounts = billingAccountRepository.findByCustomerId(uuid);

            return billingAccounts.stream().map(billingAccount -> {
                GetListBillingAccountResponse response = new GetListBillingAccountResponse();
                response.setId(billingAccount.getId());
                response.setType(billingAccount.getType());
                response.setAccountName(billingAccount.getAccountName());
                response.setAccountNumber(billingAccount.getAccountNumber());
                response.setStatus(billingAccount.getStatus());
                response.setCustomerId(billingAccount.getCustomer().getId());
                response.setAddressId(billingAccount.getAddress().getId());

                return response;
            }).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("UUID Fail");
        }
    }

    @Override
    public UpdatedBillingAccountResponse update(UpdateBillingAccountRequest request) {
        BillingAccount billingAccount = billingAccountRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Billing Account not found"));

        billingAccountBusinessRules.checkIfTypeCanBeChanged(request.getId(), request.getType());

        // Eğer adres değişiyorsa kontrol et
        if (!billingAccount.getAddress().getId().toString().equals(request.getAddressId())) {
            billingAccountBusinessRules.checkIfAddressBelongsToCustomer(
                    request.getAddressId(),
                    request.getCustomerId()
            );

            // 🔹 Yeni adresi veritabanından getir ve set et
            Address newAddress = addressRepository.findById(request.getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found"));
            billingAccount.setAddress(newAddress);
        }

        // Status değişimi kontrolü
        if (request.getStatus() != null && !request.getStatus().equals(billingAccount.getStatus().toString())) {
            billingAccountBusinessRules.checkIfStatusTransitionIsValid(
                    request.getId(),
                    request.getStatus()
            );
        }

        // Mapper address'i değiştirmesin diye özel alanları hariç güncelliyoruz
        BillingAccountMapper.INSTANCE.updateBillingAccountFromRequest(request, billingAccount);

        // Tekrar address'i elle override etme
        if (request.getAddressId() != null) {
            Address currentAddress = addressRepository.findById(request.getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found"));
            billingAccount.setAddress(currentAddress);
        }

        BillingAccount updated = billingAccountRepository.save(billingAccount);


        UpdatedBillingAccountEvent event =
                new UpdatedBillingAccountEvent(updated.getId().toString(),
                        updated.getCustomer().getId().toString(),
                        updated.getAddress().getId().toString(),
                        updated.getType().toString(),
                        updated.getStatus().toString(),
                        updated.getAccountNumber(),
                        updated.getAccountName());

        updatedBillingAccountProducer.produceBillingAccountUpdated(event);

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

        DeletedBillingAccountEvent event =
                new DeletedBillingAccountEvent(billingAccount.getId().toString(),
                        billingAccount.getCustomer().getId().toString(),
                        billingAccount.getAddress().getId().toString(),
                        billingAccount.getType().toString(),
                        billingAccount.getStatus().toString(),
                        billingAccount.getAccountNumber(),
                        billingAccount.getAccountName(),
                        billingAccount.getDeletedDate().toString());

        deletedBillingAccountProducer.produceBillingAccountDeleted(event);
        billingAccountRepository.delete(billingAccount);
    }

    @Override
    public void softDelete(UUID id) {
        BillingAccount billingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Billing Account not found"));
        billingAccount.setDeletedDate(LocalDateTime.now());

        DeletedBillingAccountEvent event =
                new DeletedBillingAccountEvent(billingAccount.getId().toString(),
                        billingAccount.getCustomer().getId().toString(),
                        billingAccount.getAddress().getId().toString(),
                        billingAccount.getType().toString(),
                        billingAccount.getStatus().toString(),
                        billingAccount.getAccountNumber(),
                        billingAccount.getAccountName(),
                        billingAccount.getDeletedDate().toString());

        deletedBillingAccountProducer.produceBillingAccountDeleted(event);
        billingAccountRepository.save(billingAccount);
    }
}
