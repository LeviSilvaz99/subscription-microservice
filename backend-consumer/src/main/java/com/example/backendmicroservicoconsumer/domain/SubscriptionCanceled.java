package com.example.backendmicroservicoconsumer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubscriptionCanceled {

    @Id
    private Integer code;

    private String user;

    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
