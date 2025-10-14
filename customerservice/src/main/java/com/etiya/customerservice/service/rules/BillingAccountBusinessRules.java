package com.etiya.customerservice.service.rules;


import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.BillingAccount;
import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.repository.BillingAccountRepository;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

@Service
public class BillingAccountBusinessRules {
    private final BillingAccountRepository billingAccountRepository;
    private final AddressRepository addressRepository;
    private final CustomerService customerService;  // ← EKLE!
    private final LocalizationService localizationService;


    public BillingAccountBusinessRules(BillingAccountRepository billingAccountRepository, AddressRepository addressRepository, CustomerService customerService, LocalizationService localizationService) {
        this.billingAccountRepository = billingAccountRepository;
        this.addressRepository = addressRepository;
        this.customerService = customerService;
        this.localizationService = localizationService;
    }

    public void checkIfBillingAccountCanBeDeleted(int id){
        BillingAccount billingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(localizationService.getMessage(Messages.BillingAccountIdDoesntExist) + " " + id));
        if(billingAccount.getStatus() == BillingAccountStatus.ACTIVE){
            throw new BusinessException(localizationService.getMessage(Messages.ActiveBillingAccountDeleting));
        }
    }

    public void checkIfTypeCanBeChanged(int id, BillingAccountType newType){
        BillingAccount existingBillingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(localizationService.getMessage(Messages.BillingAccountIdDoesntExist) + " " + id));

        if(existingBillingAccount.getType() != newType){
            throw new BusinessException(localizationService.getMessage(Messages.BillingAccountTypeChange));
        }
    }

    public void checkIfAddressBelongsToCustomer(int addressId, int customerId){
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.AddressIdDoesntExist)));
        if(address.getCustomer().getId() != customerId){
            throw new BusinessException(localizationService.getMessage(Messages.AddressBelongsToCustomer));
        }
    }

    public void checkIfStatusTransitionIsValid(int id, BillingAccountStatus newStatus){
       BillingAccount existingBillingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(localizationService.getMessage(Messages.BillingAccountIdDoesntExist) + " " + id));
         BillingAccountStatus currentStatus = existingBillingAccount.getStatus();

         if(currentStatus == newStatus){
                return; // No change in status
         }

         if(currentStatus == BillingAccountStatus.CLOSED){
                throw new BusinessException(localizationService.getMessage(Messages.ClosedBillingAccountStatusChange));
         }

    }

    public void checkIfCustomerTypeMatchesAccountType(int customerId, BillingAccountType accountType) {
        String customerType = customerService.getCustomerType(customerId);

        if (customerType.equals("INDIVIDUAL") && accountType != BillingAccountType.INDIVIDUAL) {
            throw new BusinessException(
                    localizationService.getMessage(Messages.IndividualCustomersToIndividualBillingAccounts
            ));
        }

        if (customerType.equals("CORPORATE") && accountType != BillingAccountType.CORPORATE) {
            throw new BusinessException(
                    localizationService.getMessage(Messages.CorporateCustomersToCorporateBillingAccounts)
            );
        }
    }


    public void checkIfCustomerHasAddress(int customerId) {
        if (!addressRepository.existsByCustomerId(customerId)) {
            throw new BusinessException(
                    localizationService.getMessage(Messages.CustomerMustHaveAnAddressForOpeningBillingAccount)
            );
        }
    }
}
