package com.example.qlqa.model;

public class Account {

    private int idAccount;


    private String username;

    private String password;

    private Boolean typeAccount;

    public Account(){}

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Account(int idAccount, String username, String password, Boolean typeAccount) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.typeAccount = typeAccount;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
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

    public Boolean getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(Boolean typeAccount) {
        this.typeAccount = typeAccount;
    }
}
