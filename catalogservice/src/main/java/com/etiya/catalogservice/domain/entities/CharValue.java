package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

//Bu, char_id sütununun benzersiz olduğu anlamına gelir.
// Yani, her bir özellik (characteristic) için yalnızca bir adet değer olabilir.
// Bu, genellikle bir ana özellik tablosu (lookup table) için kullanılan bir kısıtlamadır
// ve bir Özelliğin sadece tek bir zorunlu değeri olmasını sağlar.
// UNIQUE KEY kısıtlaması için @Table annotation'ı kullanıldı
@Entity
@Table(name = "char_values", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"char_id", "value"})
})
public class CharValue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "value", length = 100, nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "char_id", nullable = false)
    // Unique kısıtlaması nedeniyle: Bir CharValue sadece bir Characteristic'e bağlıdır
    // ve bu characteristic_id (char_id) değeri bu tabloda tekrar edemez.
    private Characteristic characteristic;

    @OneToMany(mappedBy = "charValue")
    private List<ProdCharValue> productCharValues;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public List<ProdCharValue> getProductCharValues() {
        return productCharValues;
    }

    public void setProductCharValues(List<ProdCharValue> productCharValues) {
        this.productCharValues = productCharValues;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public CharValue() {
    }

    public CharValue(UUID id, String value) {
        this.id = id;
        this.value = value;
    }

    public CharValue(UUID id, String value, Characteristic characteristic, List<ProdCharValue> productCharValues) {
        this.id = id;
        this.value = value;
        this.characteristic = characteristic;
        this.productCharValues = productCharValues;
    }
}
