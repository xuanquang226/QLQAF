package com.example.qlqa.model;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LinearLayoutQuantity {
    private ImageButton imgBtnIncrease;
    private ImageButton imgBtnDecrease;
    private TextView tvQuantity;

    public LinearLayoutQuantity() {
    }

    public LinearLayoutQuantity(ImageButton imgBtnDecrease, ImageButton imgBtnIncrease, TextView tvQuantity){
        this.imgBtnDecrease = imgBtnDecrease;
        this.imgBtnIncrease = imgBtnIncrease;
        this.tvQuantity = tvQuantity;
    }

    public ImageButton getImgBtnIncrease() {
        return imgBtnIncrease;
    }

    public void setImgBtnIncrease(ImageButton imgBtnIncrease) {
        this.imgBtnIncrease = imgBtnIncrease;
    }

    public ImageButton getImgBtnDecrease() {
        return imgBtnDecrease;
    }

    public void setImgBtnDecrease(ImageButton imgBtnDecrease) {
        this.imgBtnDecrease = imgBtnDecrease;
    }

    public TextView getTvQuantity() {
        return tvQuantity;
    }

    public void setTvQuantity(TextView tvQuantity) {
        this.tvQuantity = tvQuantity;
    }
}
