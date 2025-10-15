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

}
