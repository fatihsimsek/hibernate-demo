package com.simsek.hibernate.adapter;

import javax.validation.constraints.NotNull;

public class AddressCreateRequest {
    @NotNull
    private String city;
    @NotNull
    private String district;
    @NotNull
    private String description;
    @NotNull
    private String customerId;
    private boolean billingAddress;
    private boolean contactAddress;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public boolean isBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(boolean billingAddress) {
        this.billingAddress = billingAddress;
    }

    public boolean isContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(boolean contactAddress) {
        this.contactAddress = contactAddress;
    }
}
