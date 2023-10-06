package com.example.qlqa.model;

import java.util.List;

public class DinnerTable {
    private long id;

    private boolean stt;
    private List<Order> order;

    public DinnerTable() {
    }

    public DinnerTable(long oNumber, boolean stt) {
        this.id = oNumber;
        this.stt = stt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStt() {
        return stt;
    }

    public void setStt(boolean stt) {
        this.stt = stt;
    }
}
