package com.simsek.hibernate.adapter;

import javax.validation.constraints.NotNull;

public class OrderCreateRequest {
    @NotNull
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
