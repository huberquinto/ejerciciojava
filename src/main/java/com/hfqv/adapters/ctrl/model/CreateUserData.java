package com.hfqv.adapters.ctrl.model;

import java.util.List;

public class CreateUserData {

    private String name;
    private String email;
    private String password;
    private List<UserPhoneData> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserPhoneData> getPhones() {
        return phones;
    }

    public void setPhones(List<UserPhoneData> phones) {
        this.phones = phones;
    }
}
