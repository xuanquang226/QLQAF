package com.example.qlqa.model;

public class TupleAccountToken<K, V>{
    private K token;
    private V account;

    public TupleAccountToken() {
    }

    public TupleAccountToken(K token, V account) {
        this.token = token;
        this.account = account;
    }

    public K getToken() {
        return token;
    }

    public void setToken(K token) {
        this.token = token;
    }

    public V getAccount() {
        return account;
    }

    public void setAccount(V account) {
        this.account = account;
    }
}
