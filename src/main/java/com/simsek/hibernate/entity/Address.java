package com.simsek.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", foreignKey = @ForeignKey(name="FK_address_customer"))
    private Customer customer;

    public Address(String city, String district, String description) {
        this.city = city;
        this.district = district;
        this.description = description;
    }

    public Address(String city, String district, String description, Customer customer) {
        this.city = city;
        this.district = district;
        this.description = description;
        this.customer = customer;
    }

    public Address() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
