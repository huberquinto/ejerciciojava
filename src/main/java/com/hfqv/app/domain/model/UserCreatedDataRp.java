package com.hfqv.app.domain.model;

import java.util.Date;

public class UserCreatedDataRp {

    private String Id;

    private Date created;

    private Date modified;

    private String token;

    private Boolean isActive;

    private Date lastLogin;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
