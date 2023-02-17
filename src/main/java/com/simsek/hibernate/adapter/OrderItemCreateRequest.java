package com.simsek.hibernate.adapter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItemCreateRequest {
    @NotNull
    @Min(1)
    private Integer quantity;
    @NotNull
    private String orderId;
    @NotNull
    private String productId;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
