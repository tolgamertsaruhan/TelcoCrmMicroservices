package com.etiya.customerservice.domain.entities;


import com.etiya.common.entities.BaseEntity;
import com.etiya.customerservice.domain.enums.ContactMediumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contactmediums")
@SQLRestriction("deleted_date IS NULL")

public class ContactMedium extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ContactMediumType type;

    @Column(name = "value")
    private String value;

    @Column(name = "isPrimary")
    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ContactMediumType getType() {
        return type;
    }

    public void setType(ContactMediumType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ContactMedium() {
    }

    public ContactMedium(UUID id, ContactMediumType type, String value, boolean isPrimary, Customer customer) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customer = customer;
    }
}
