package com.etiya.catalogservice.domain.entities;


import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "characteristics")
public class Characteristic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // Bandwith , Commitment Period, PSTN No, Modem SN
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    // Maksimum indirme hızı
    // Taahhüt süresi
    // Modem seri numarası
    @Column(name = "description")
    private String description;

    // Örnek db de bu değer Number String olarak girilmiş.
    @Column(name = "data_type", length = 20, nullable = false)
    private String dataType;

    // Örnek db de Mbps , Ay , Yok girilmiş.
    @Column(name = "unit_of_measure", length = 20)
    private String unitOfMeasure;

    //orphanRemoval = true Ne Anlama Gelir?
    //Bu özellik, ilişkiden çıkarılan veya ebeveyn (parent) entity tarafından
    // artık başvurulmayan çocuk (child) entity'lerin veritabanından
    // otomatik olarak silinmesini sağlar.

    // 1. char_values (Bir özelliğin birden fazla değeri olabilir)
    @OneToMany(mappedBy = "characteristic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharValue> charValues; // CharValue: char_values tablosunun Entity'si

    // 2. product_spec_characteristics
    @OneToMany(mappedBy = "characteristic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSpecCharacteristic> productSpecCharacteristics;
    // ProductSpecCharacteristic: product_spec_characteristics tablosunun Entity'si

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public List<CharValue> getCharValues() {
        return charValues;
    }

    public void setCharValues(List<CharValue> charValues) {
        this.charValues = charValues;
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



    public Characteristic() {
    }

    public Characteristic(UUID id, String name, String description, String dataType, String unitOfMeasure) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Characteristic(UUID id, String name, String description, String dataType, String unitOfMeasure, List<CharValue> charValues, List<ProductSpecCharacteristic> productSpecCharacteristics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.unitOfMeasure = unitOfMeasure;
        this.charValues = charValues;
        this.productSpecCharacteristics = productSpecCharacteristics;
    }
}
