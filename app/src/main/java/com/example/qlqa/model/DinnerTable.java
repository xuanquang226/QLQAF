package com.example.qlqa.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public class DinnerTable {
    private long oNumber;

    private Order order;

    public DinnerTable() {
    }

    public DinnerTable(long oNumber, Order order) {
        this.oNumber = oNumber;
        this.order = order;
    }

    public long getoNumber() {
        return oNumber;
    }

    public void setoNumber(long oNumber) {
        this.oNumber = oNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
