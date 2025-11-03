package com.etiya.catalogservice.domain.entities;

import com.etiya.catalogservice.domain.enums.CustomerOfferStatuses;
import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer_offers")
public class CustomerOffer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

   /* // 2. customer_id (FOREIGN KEY - ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // İlişkili Customer Entity'si (Varsayımsal)*/

    // 3. product_offer_id (FOREIGN KEY - ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_offer_id", nullable = false)
    private ProductOffer productOffer; // İlişkili ProductOffer Entity'si (Varsayımsal)

    // 4. expiration_date
    @Column(name = "expiration_date") // Not Null değil
    private LocalDateTime expirationDate; // date -> LocalDate

    // 5. status
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private CustomerOfferStatuses status; // varchar(50) -> String

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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CustomerOfferStatuses getStatus() {
        return status;
    }

    public void setStatus(CustomerOfferStatuses status) {
        this.status = status;
    }

    public CustomerOffer() {
    }

    public CustomerOffer(UUID id, ProductOffer productOffer, LocalDateTime expirationDate, CustomerOfferStatuses status) {
        this.id = id;
        this.productOffer = productOffer;
        this.expirationDate = expirationDate;
        this.status = status;
    }
}
