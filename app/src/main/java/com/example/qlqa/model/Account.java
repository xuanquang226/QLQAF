package com.example.qlqa.model;

public class Account {

    private long idS;

    private String username;

    private String password;

    private boolean typeA;

    public Account(){}

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Account(long idAccount, String username, String password, boolean typeA) {
        this.idS = idAccount;
        this.username = username;
        this.password = password;
        this.typeA = typeA;
    }

    public long getIdS() {
        return idS;
    }

    public void setIdS(long idS) {
        this.idS = idS;
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

    public boolean getTypeAccount() {
        return typeA;
    }

    public void setTypeAccount(Boolean typeAccount) {
        this.typeA = typeAccount;
    }
}
