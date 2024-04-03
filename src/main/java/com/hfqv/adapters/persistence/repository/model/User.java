package com.hfqv.adapters.persistence.repository.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="TblUser")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="userId")
    private UUID userId;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="createdDate")
    private Date created;

    @Column(name="modifiedDate")
    private Date modified;

    @Column(name="token")
    private String token;

    @Column(name="isActive")
    private Boolean isActive;

    @Column(name="lastLogin")
    private Date lastLogin;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Phone> phones;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
