package com.etiya.catalogservice.domain.entities;

import com.etiya.catalogservice.domain.enums.ProductOfferStatuses;
import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product_offers")
public class ProductOffer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // Temel internet teklifi
    // Ücretsiz kurulum
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "discount_rate", precision = 3, scale = 2, nullable = false)
    private BigDecimal discountRate;

    // Bu alanı Enum olarak modellemek iyi bir uygulamadır
    // ACTIVE
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductOfferStatuses status;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 1 ürün teklifi birden fazla catalog product offer sahip olablr
    @OneToMany(mappedBy = "productOffer")
    private List<CatalogProductOffer> catalogProductOffers;

    // 1 ürün teklifi birden fazla customer offer a sahip olabilir
    @OneToMany(mappedBy = "productOffer")
    private List<CustomerOffer> customerOffers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<CatalogProductOffer> getCatalogProductOffers() {
        return catalogProductOffers;
    }

    public void setCatalogProductOffers(List<CatalogProductOffer> catalogProductOffers) {
        this.catalogProductOffers = catalogProductOffers;
    }

    public List<CustomerOffer> getCustomerOffers() {
        return customerOffers;
    }

    public void setCustomerOffers(List<CustomerOffer> customerOffers) {
        this.customerOffers = customerOffers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public ProductOfferStatuses getStatus() {
        return status;
    }

    public void setStatus(ProductOfferStatuses status) {
        this.status = status;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public ProductOffer() {
    }

    public ProductOffer(UUID id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, BigDecimal discountRate, ProductOfferStatuses status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountRate = discountRate;
        this.status = status;
    }

    public ProductOffer(UUID id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, BigDecimal discountRate, ProductOfferStatuses status, Product product, List<CatalogProductOffer> catalogProductOffers, List<CustomerOffer> customerOffers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountRate = discountRate;
        this.status = status;
        this.product = product;
        this.catalogProductOffers = catalogProductOffers;
        this.customerOffers = customerOffers;
    }
}
