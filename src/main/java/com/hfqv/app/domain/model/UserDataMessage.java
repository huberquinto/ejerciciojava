package com.hfqv.app.domain.model;

import com.hfqv.adapters.ctrl.model.UserPhoneData;

import java.util.List;

public class UserDataMessage {


    private String id;
    private String name;
    private String email;
    private String password;
    private List<PhoneDataMessage> phones;

    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<PhoneDataMessage> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataMessage> phones) {
        this.phones = phones;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
