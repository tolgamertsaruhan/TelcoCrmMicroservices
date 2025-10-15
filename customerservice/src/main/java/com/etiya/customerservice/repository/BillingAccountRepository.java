package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillingAccountRepository extends JpaRepository<BillingAccount, UUID> {
}
