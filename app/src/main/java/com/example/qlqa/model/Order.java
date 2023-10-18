package com.example.qlqa.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

    private long id;

    private Set<DishOrder> listMonAn = new HashSet<DishOrder>();

    private DinnerTable dinnerTable;

    private Staff staff;


    private Timestamp dateCreate;

    private boolean state;

    private double totalPrice;

    public Order() {

    }

    public Order(long id, DinnerTable dinnerTable, Staff staff, Timestamp date_create, boolean state) {
        this.id = id;
        this.dinnerTable = dinnerTable;
        this.staff = staff;
        this.dateCreate = date_create;
        this.state = state;
    }


    public Timestamp getDate() {
        return dateCreate;
    }

    public void setDate(Timestamp date_create) {
        this.dateCreate = date_create;
    }

    public long getId() {
        return id;
    }


    public void setId(int idOrder) {
        this.id = idOrder;
    }


    public Set<DishOrder> getListMonAn() {
        return listMonAn;
    }


    public void setListMonAn(Set<DishOrder> listMonAn) {
        this.listMonAn = listMonAn;
    }


    public DinnerTable getDinnerTable() {
        return dinnerTable;
    }


    public void setDinnerTable(DinnerTable dinnerTable) {
        this.dinnerTable = dinnerTable;
    }


    public Staff getStaff() {
        return staff;
    }


    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public boolean isState() {
        return state;
    }


    public void setState(boolean state) {
        this.state = state;
    }


    public void setMonAn(DishOrder dishOrder) {
        this.listMonAn.add(dishOrder);
    }

    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice(){
        return totalPrice;
    }
}
