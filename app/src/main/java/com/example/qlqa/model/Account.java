package com.example.qlqa.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private String username;
    private String password;
    private Boolean typeA;

    private List<Role> lRole = new ArrayList<>();
    public Account() {

    }

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Account(long id, String username, String password, Boolean typeA) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.typeA = typeA;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean isTypeA() {
        return typeA;
    }
    public void setTypeA(Boolean typeA) {
        this.typeA = typeA;
    }
    public List<Role> getlRole() {
        return lRole;
    }
    public void setlRole(List<Role> lRole) {
        this.lRole = lRole;
    }

}
