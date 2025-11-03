package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "catalogs")
public class Catalog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    // 3. parent_id - Hiyerarşik İlişki (ManyToOne)
    // Bu, "parent_id" sütununun "catalogs" tablosunun kendisine
    // Foreign Key olarak atıfta bulunduğunu gösterir.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // Sütun adı parent_id'dir, nullable=true (default) olduğu için belirtmeye gerek yok
    private Catalog parent; // Kendi sınıfına referans verir


    // 4. Çocuk Kataloglar (Hierarchy'nin diğer tarafı - OneToMany)
    // Bir ana kataloğun birden fazla alt kataloğu olabilir.
    // "parent" alanıyla eşleştiğini belirtir.
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Catalog> children;

    // 1 katalog birden fazla ürüne sahip olabilir.
    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
    private List<Product> products;

    // 1 katalogta birden fazla product offer olabilir.
    @OneToMany(mappedBy = "catalog")
    private List<CatalogProductOffer> catalogProductOffers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catalog getParent() {
        return parent;
    }

    public void setParent(Catalog parent) {
        this.parent = parent;
    }

    public List<Catalog> getChildren() {
        return children;
    }

    public void setChildren(List<Catalog> children) {
        this.children = children;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<CatalogProductOffer> getCatalogProductOffers() {
        return catalogProductOffers;
    }

    public void setCatalogProductOffers(List<CatalogProductOffer> catalogProductOffers) {
        this.catalogProductOffers = catalogProductOffers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public Catalog() {
    }

    public Catalog(UUID id, String name, Catalog parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public Catalog(UUID id, String name, Catalog parent, List<Catalog> children, List<Product> products) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
        this.products = products;
    }

    public Catalog(UUID id, String name, Catalog parent, List<Catalog> children, List<Product> products, List<CatalogProductOffer> catalogProductOffers) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
        this.products = products;
        this.catalogProductOffers = catalogProductOffers;
    }
}
