package com.etiya.catalogservice.domain.entities;


import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
// Aynı ürün-kampanya kombinasyonunun tekrar etmesini engelleyen bir Bileşik UNIQUE KEY kısıtlamasına sahiptir.
// Veritabanındaki 'campaign_products_product_id_campaign_id_key'
// kısıtlaması, JPA'da @Table annotation'ı içinde tanımlanabilir:
@Table(name = "campaign_products_offers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_offer_id", "campaign_id"})})
public class CampaignProductOffer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // campaign_id (Yabancı Anahtar - Campaign Entity'sine bağlanır)
    @ManyToOne(fetch = FetchType.LAZY) // Bir ürün kaydı, tek bir kampanyaya bağlıdır
    @JoinColumn(name = "campaign_id", nullable = false) // Veritabanındaki sütun adı
    private Campaign campaign;

    // product_id (Yabancı Anahtar - Product Entity'sine bağlanır)
    @ManyToOne(fetch = FetchType.LAZY) // Bir ürün kaydı, tek bir ürüne bağlıdır
    @JoinColumn(name = "product_offer_id", nullable = false) // Veritabanındaki sütun adı
    private ProductOffer productOffer; // Product: Ürün Entity'sinin adı olmalı

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CampaignProductOffer() {
    }

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }

    public CampaignProductOffer(UUID id, Campaign campaign, ProductOffer productOffer) {
        this.id = id;
        this.campaign = campaign;
        this.productOffer = productOffer;
    }
}
