package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

//spec_id ve characteristic_id sütunlarının birleşimi benzersizdir.
// Yani, bir özellik seti, aynı özelliği birden fazla kez içeremez.
@Entity
@Table(name = "product_spec_characteristics", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"spec_id", "characteristic_id"})
})
public class ProductSpecCharacteristic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "is_required", nullable = false)
    private boolean requiredIs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_id", nullable = false)
    private ProductSpecification productSpecification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "characteristic_id", nullable = false)
    private Characteristic characteristic;

    public boolean requiredIs() {
        return requiredIs;
    }

    public void setRequired(boolean required) {
        requiredIs = required;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isRequiredIs() {
        return requiredIs;
    }

    public void setRequiredIs(boolean requiredIs) {
        this.requiredIs = requiredIs;
    }

    public ProductSpecCharacteristic() {
    }

    public ProductSpecCharacteristic(UUID id, boolean requiredIs, ProductSpecification productSpecification, Characteristic characteristic) {
        this.id = id;
        this.requiredIs = requiredIs;
        this.productSpecification = productSpecification;
        this.characteristic = characteristic;
    }

    public ProductSpecCharacteristic(UUID id, boolean requiredIs) {
        this.id = id;
        this.requiredIs = requiredIs;
    }
}
