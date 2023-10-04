package com.example.qlqa.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Set;

public class DinnerTable implements Serializable {
    private int oNumber;
    private boolean stt;

    private Set<Order> order;
    public DinnerTable() {
    }

    public DinnerTable(int oNumber, boolean stt) {
        this.oNumber = oNumber;
        this.stt = stt;
    }

    public int getoNumber() {
        return oNumber;
    }

    public void setoNumber(int oNumber) {
        this.oNumber = oNumber;
    }

    public boolean isStt() {
        return stt;
    }

    public void setStt(boolean stt) {
        this.stt = stt;
    }

    public Set<Order> getOrder() {
        return order;
    }
}
