package com.simsek.hibernate.adapter;

import javax.validation.constraints.NotNull;

public class CustomerUpdateRequest {
    @NotNull
    private String id;
    @NotNull
    private String fullname;
    @NotNull
    private String birthDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
