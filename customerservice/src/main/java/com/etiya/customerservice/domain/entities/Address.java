package com.etiya.customerservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
@SQLRestriction("deleted_date IS NULL")

public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "houseNumber")
    private String houseNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "isDefault")
    private boolean isDefault;

    @ManyToOne()
    @JoinColumn(name = "districtId")
    private District district;

    @ManyToOne()
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "address")
    public List<BillingAccount> billingAccounts;


}
