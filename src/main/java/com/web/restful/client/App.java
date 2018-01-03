package com.web.restful.client;

import java.io.Serializable;

/**
*
* @author qushiwen
* @create 2017-12-27
* @version 1.0
**/
public class App implements Serializable{

    private static final long serialVersionUID = 5534527797461561003L;

    private String name;

    private String phone;

    private String email;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }
}
