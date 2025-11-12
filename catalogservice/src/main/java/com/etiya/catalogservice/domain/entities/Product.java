package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // 8 mbps temel internet SKU
    // Standart modem cihazı
    // kurulum ve aktivasyon ücreti
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "discounted_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal discountedPrice;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_offer_id", nullable = false)
    private ProductOffer productOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id", nullable = false)
    private Catalog catalog;

    // Ürüne özel teknik özellikleri işaret eder.
    // Spec_id Not Null olduğu için nullable = false olmalı.
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "spec_id", nullable = false)
//    private ProductSpecification productSpecification;

    // prod_char_values tablosundan gelen ilişki
    // Bir ürünün birden fazla özellik değeri olabilir.
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<ProdCharValue> productCharValues;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

//    public ProductSpecification getProductSpecification() {
//        return productSpecification;
//    }
//
//    public void setProductSpecification(ProductSpecification productSpecification) {
//        this.productSpecification = productSpecification;
//    }

//    public List<ProdCharValue> getProductCharValues() {
//        return productCharValues;
//    }
//
//    public void setProductCharValues(List<ProdCharValue> productCharValues) {
//        this.productCharValues = productCharValues;
//    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }

    public Product() {
    }

    public Product(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(UUID id, String name, String description, BigDecimal price, BigDecimal discountedPrice, ProductOffer productOffer, Catalog catalog) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.productOffer = productOffer;
        this.catalog = catalog;
    }


}
