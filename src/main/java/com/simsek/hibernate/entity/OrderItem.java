package com.simsek.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="orderItem")
public class OrderItem {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    @Column
    private LocalDateTime createDate;

    @Column
    private Integer quantity;

    @Column
    private BigDecimal amount;

    @Column
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name="FK_orderItem_order"))
    private Order order;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", foreignKey = @ForeignKey(name="FK_orderItem_product"))
    private Product product;

    public OrderItem(UUID id, LocalDateTime createDate, Integer quantity, BigDecimal amount, String status, Order order, Product product) {
        this.id = id;
        this.createDate = createDate;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
        this.order = order;
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
