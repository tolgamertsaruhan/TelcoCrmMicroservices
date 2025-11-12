package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

//char_value_id ve product_id sütunlarının birleşimi benzersizdir.
// Yani, bir ürün aynı özellik değerini birden fazla kez kullanamaz.
// UNIQUE KEY kısıtlamasını Entity seviyesinde tanımlıyoruz
@Entity
@Table(name = "prod_char_values", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"char_value_id", "product_offer_id"}, name = "UK_prod_char_value")
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
    @JoinColumn(name = "product_offer_id", nullable = false)
    private ProductOffer productOffer; // Hangi ürüne ait

    public CharValue getCharValue() {
        return charValue;
    }

    public void setCharValue(CharValue charValue) {
        this.charValue = charValue;
    }

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProdCharValue() {
    }

    public ProdCharValue(UUID id, CharValue charValue, ProductOffer productOffer) {
        this.id = id;
        this.charValue = charValue;
        this.productOffer = productOffer;
    }
}
