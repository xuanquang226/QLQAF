package com.example.qlqa.model;

import java.util.ArrayList;
import java.util.List;

public class Role {

    private long id;
    private String name;

    private List<Account> lAccount = new ArrayList<>();

    public Role() {
    }

    public Role(long id, String name, List<Account> lAccount) {
        this.id = id;
        this.name = name;
        this.lAccount = lAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getlAccount() {
        return lAccount;
    }

    public void setlAccount(List<Account> lAccount) {
        this.lAccount = lAccount;
    }

}
