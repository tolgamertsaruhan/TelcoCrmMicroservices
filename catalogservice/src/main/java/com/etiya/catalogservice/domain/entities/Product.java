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

    @Column(name = "description")
    private String description;

    @Column(name = "stock")
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id", nullable = false)
    private Catalog catalog;

    // Ürüne özel teknik özellikleri işaret eder.
    // Spec_id Not Null olduğu için nullable = false olmalı.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_id", nullable = false)
    private ProductSpecification productSpecification;

    // prod_char_values tablosundan gelen ilişki
    // Bir ürünün birden fazla özellik değeri olabilir.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProdCharValue> productCharValues;

    // 1 ürün birçok kampanyaya sahip olabilir.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CampaignProduct> campaignProducts;

    // 1 ürün için birden fazla product offer sunulabilinir.
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOffer> productOffers;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public List<ProdCharValue> getProductCharValues() {
        return productCharValues;
    }

    public void setProductCharValues(List<ProdCharValue> productCharValues) {
        this.productCharValues = productCharValues;
    }

    public List<CampaignProduct> getCampaignProducts() {
        return campaignProducts;
    }

    public void setCampaignProducts(List<CampaignProduct> campaignProducts) {
        this.campaignProducts = campaignProducts;
    }

    public List<ProductOffer> getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(List<ProductOffer> productOffers) {
        this.productOffers = productOffers;
    }

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

    public Product() {
    }

    public Product(UUID id, String name, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(UUID id, String name, BigDecimal price, int stock, Catalog catalog, ProductSpecification productSpecification, List<ProdCharValue> productCharValues, List<CampaignProduct> campaignProducts, List<ProductOffer> productOffers) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.catalog = catalog;
        this.productSpecification = productSpecification;
        this.productCharValues = productCharValues;
        this.campaignProducts = campaignProducts;
        this.productOffers = productOffers;
    }

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
