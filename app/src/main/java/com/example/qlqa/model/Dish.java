package com.example.qlqa.model;

import java.util.HashSet;
import java.util.Set;

public class Dish {

    private long id;
    private String name;
    private int quantity;
    private double price;

    private Set<Order> listOrder = new HashSet<Order>();

    public Dish() {}

    public Dish(long id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public double getPrice() {
        return price;
    }


    public void setPrice(double d) {
        this.price = d;
    }

}
