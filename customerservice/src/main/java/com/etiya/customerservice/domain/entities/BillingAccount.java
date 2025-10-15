package com.etiya.customerservice.domain.entities;


import com.etiya.common.entities.BaseEntity;
import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "billingAccounts") //-> billing_account
@SQLRestriction("deleted_date IS NULL")

public class BillingAccount extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BillingAccountType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BillingAccountStatus status;

    @Column(name = "account_number", unique = true)
    private String accountNumber;


    @Column(name = "account_name")
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @PrePersist
    public void generateAccountNumber() {
        String prefix = "BILL-";
        String year = String.valueOf(java.time.Year.now().getValue());
        String randomPart = String.format("%04d", new java.util.Random().nextInt(10000));
        this.accountNumber = prefix + year + "-" + randomPart;
    }

}
