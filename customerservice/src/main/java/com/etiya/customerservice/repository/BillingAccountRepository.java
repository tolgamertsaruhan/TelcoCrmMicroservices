package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BillingAccountRepository extends JpaRepository<BillingAccount, UUID> {

    List<BillingAccount> findByCustomerId(UUID id);
}
