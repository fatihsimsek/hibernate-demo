package com.simsek.hibernate.adapter;

import javax.validation.constraints.NotNull;

public class CustomerCreateRequest {
    @NotNull
    private String fullname;
    @NotNull
    private String birthDate;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
