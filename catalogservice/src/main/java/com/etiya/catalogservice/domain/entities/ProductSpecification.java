package com.etiya.catalogservice.domain.entities;

import com.etiya.catalogservice.domain.enums.ProductSpecificationLifeCycleStatuses;
import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product_specifications")
public class ProductSpecification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // 2. name
    // ADSL Internet model , ADSL Modem Model , Kurulum servisi model
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    // 3. description
    // 8 mbps e kadar internet hizmeti
    // Standart modem cihazının teknik tanımı.
    //Yeni hizmetin aktivasyonu için gereken süreç.
    @Column(name = "description") // Not Null değil
    private String description; // text -> String

    // 4. lifecycle_status  ACTIVE
    @Enumerated(EnumType.STRING)
    @Column(name = "lifecycle_status", nullable = false)
    private ProductSpecificationLifeCycleStatuses lifecycleStatus; // Veya Enum (LifecycleStatus) kullanılabilir

    // 5. product_type
    //Service  , Device
    @Column(name = "product_type", length = 50, nullable = false)
    private String productType;

    // Bir özellik seti birden fazla ürüne ait olabilir.
    @OneToMany(mappedBy = "productSpecification", cascade = CascadeType.ALL)
    private List<Product> products;

    // Bir özellik seti birden fazla Characteristic içerir.
    @OneToMany(mappedBy = "productSpecification", cascade = CascadeType.ALL)
    private List<ProductSpecCharacteristic> productSpecCharacteristics;

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<ProductSpecCharacteristic> getProductSpecCharacteristics() {
        return productSpecCharacteristics;
    }

    public void setProductSpecCharacteristics(List<ProductSpecCharacteristic> productSpecCharacteristics) {
        this.productSpecCharacteristics = productSpecCharacteristics;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductSpecificationLifeCycleStatuses getLifecycleStatus() {
        return lifecycleStatus;
    }

    public void setLifecycleStatus(ProductSpecificationLifeCycleStatuses lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }

    public ProductSpecification() {
    }

    public ProductSpecification(UUID id, String name, String description, ProductSpecificationLifeCycleStatuses lifecycleStatus, String productType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lifecycleStatus = lifecycleStatus;
        this.productType = productType;
    }

    public ProductSpecification(UUID id, String name, String description, ProductSpecificationLifeCycleStatuses lifecycleStatus, String productType, List<Product> products, List<ProductSpecCharacteristic> productSpecCharacteristics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lifecycleStatus = lifecycleStatus;
        this.productType = productType;
        this.products = products;
        this.productSpecCharacteristics = productSpecCharacteristics;
    }
}
