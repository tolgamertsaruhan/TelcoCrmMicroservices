package com.etiya.customerservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class CorporateCustomer extends Customer {

    @OneToOne
    @JoinColumn(name = "id")
    private Customer customer;

    @Column(name = "companyName", nullable = false, length = 100)
    private String companyName;

    @Column(name = "taxNumber", unique = true, nullable = false, length = 10)
    private String taxNumber;

    @Column(name = "companyType")
    private String companyType;
}
