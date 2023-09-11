package com.example.qlqa.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public class DinnerTable implements Serializable {
    private int oNumber;
    private boolean stt;

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


}
