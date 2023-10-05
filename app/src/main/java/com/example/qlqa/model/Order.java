package com.example.qlqa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

    private long idOrder;



    private Set<Dish> listMonAn = new HashSet<Dish>();


    private DinnerTable dinnerTable;


    private Staff staff;

    private Date date;

    private boolean state;

    public Order() {

    }

    public Order(long idOrder, Set<Dish> listMonAn, DinnerTable dinnerTable, Staff staff, Date date, boolean state) {
        this.idOrder = idOrder;
        this.listMonAn = listMonAn;
        this.dinnerTable = dinnerTable;
        this.staff = staff;
        this.date = date;
        this.state = state;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return idOrder;
    }


    public void setId(int idOrder) {
        this.idOrder = idOrder;
    }


    public Set<Dish> getListMonAn() {
        return listMonAn;
    }


    public void setListMonAn(Set<Dish> listMonAn) {
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


    public void setMonAn(Dish dish) {
        this.listMonAn.add(dish);
    }
}
