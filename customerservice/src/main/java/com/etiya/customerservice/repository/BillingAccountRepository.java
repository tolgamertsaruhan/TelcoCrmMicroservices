package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAccountRepository extends JpaRepository<BillingAccount, Integer> {
}
