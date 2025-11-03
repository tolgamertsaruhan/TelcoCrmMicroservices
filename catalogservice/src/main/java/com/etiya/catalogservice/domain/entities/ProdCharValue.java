package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

//char_value_id ve product_id sütunlarının birleşimi benzersizdir.
// Yani, bir ürün aynı özellik değerini birden fazla kez kullanamaz.
// UNIQUE KEY kısıtlamasını Entity seviyesinde tanımlıyoruz
@Entity
@Table(name = "prod_char_values", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"char_value_id", "product_id"}, name = "UK_prod_char_value")
})
public class ProdCharValue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "char_value_id", nullable = false)
    private CharValue charValue; // Hangi değere ait

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Hangi ürüne ait

    public CharValue getCharValue() {
        return charValue;
    }

    public void setCharValue(CharValue charValue) {
        this.charValue = charValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProdCharValue() {
    }

    public ProdCharValue(UUID id, CharValue charValue, Product product) {
        this.id = id;
        this.charValue = charValue;
        this.product = product;
    }
}
