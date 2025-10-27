package com.etiya.customerservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    //private int id;
    private UUID id;

    @Column(name = "customer_number")
    private String customerNumber;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ContactMedium> contactMediums;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Address> addresses;



    @PrePersist
    public void generateCustomerNumber(){
        String prefix = "CUST-";
        String year = String.valueOf(java.time.Year.now().getValue());
        String randomPart = String.format("%04d",new java.util.Random().nextInt(10000));
        this.customerNumber = prefix + year + "-" + randomPart;
    }

    public List<ContactMedium> getContactMediums() {
        return contactMediums;
    }

    public void setContactMediums(List<ContactMedium> contactMediums) {
        this.contactMediums = contactMediums;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Customer() {
    }

    public Customer(UUID id, String customerNumber, List<Address> addresses, List<ContactMedium> contactMediums) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.addresses = addresses;
        this.contactMediums = contactMediums;
    }
}


//Customerserviste => Address,City,District,BillingAccount,ContactMedium entitylerini ekleyelim.
//repositorylerini oluşturalım. Servis sınıflarını yazalım. Mapper sınıflarını yazalım. Global ex handler ekleyelim.
//Kafka nedir? araştıralım. ElasticSearch nedir? ConfigServer nedir ?