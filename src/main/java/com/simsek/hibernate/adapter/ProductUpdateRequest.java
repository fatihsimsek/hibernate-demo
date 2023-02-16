package com.simsek.hibernate.adapter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductUpdateRequest {
    @NotNull
    private String id;
    @NotNull
    private String code;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
