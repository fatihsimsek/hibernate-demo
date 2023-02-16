package com.simsek.hibernate.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private BigDecimal amount;

    public Product(String code, String description, BigDecimal amount) {
        this.code = code;
        this.description = description;
        this.amount = amount;
    }

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
