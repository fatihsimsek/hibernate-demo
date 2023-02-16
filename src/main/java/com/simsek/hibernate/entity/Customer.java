package com.simsek.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    @Column
    private String fullname;

    @Column
    private LocalDate birthDate;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="billing_address_id", foreignKey = @ForeignKey(name="FK_customer_billingAddress"))
    private Address billingAddress;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="contact_address_id", foreignKey = @ForeignKey(name="FK_customer_contactAddress"))
    private Address contactAddress;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderList = new ArrayList<>();

    public Customer(String fullname, LocalDate birthDate, Address billingAddress, Address contactAddress) {
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.billingAddress = billingAddress;
        this.contactAddress = contactAddress;
    }

    public Customer(String fullname, LocalDate birthDate) {
        this.fullname = fullname;
        this.birthDate = birthDate;
    }

    public Customer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
