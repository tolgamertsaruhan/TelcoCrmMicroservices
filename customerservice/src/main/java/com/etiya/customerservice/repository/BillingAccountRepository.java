package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BillingAccountRepository extends JpaRepository<BillingAccount, UUID> {

    List<BillingAccount> findByCustomerId(UUID id);

    @Query("SELECT ba.address.customer.id FROM BillingAccount ba WHERE ba.id = :billingAccountId")
    UUID getCustomerIdByBillingAccountId(UUID billingAccountId);


}
