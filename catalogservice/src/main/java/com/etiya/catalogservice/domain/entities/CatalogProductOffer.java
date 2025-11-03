package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

//product_offer_id ve catalog_id sütunlarının birleşimi benzersiz olmalıdır.
// Bu, aynı ürün teklifinin aynı kataloğa birden fazla kez eklenemeyeceği anlamına gelir.
// UNIQUE KEY kısıtlamasını Entity seviyesinde tanımlıyoruz
@Entity
@Table(name = "catalog_product_offers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_offer_id", "catalog_id"})
})
public class CatalogProductOffer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // catalog_id (Yabancı Anahtar)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id", nullable = false) // FK sütun adı
    private Catalog catalog; // İlişkili Catalog Entity'si

    // product_offer_id (Yabancı Anahtar)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_offer_id", nullable = false) // FK sütun adı
    private ProductOffer productOffer; // İlişkili ProductOffer Entity'si (Varsayımsal)

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
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



    public CatalogProductOffer() {
    }

    public CatalogProductOffer(UUID id, Catalog catalog, ProductOffer productOffer) {
        this.id = id;
        this.catalog = catalog;
        this.productOffer = productOffer;
    }
}
