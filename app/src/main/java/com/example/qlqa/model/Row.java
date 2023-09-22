package com.example.qlqa.model;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Row {
    private TextView tvNameDish;
    private LinearLayout linearLayoutQuantity;
    private TextView tvPrice;
    private TextView tvNote;

    private ImageButton imgBtnIncrease;
    private ImageButton imgBtnDecrease;
    private TextView tvQuantity;

    public Row(){
    }

    public Row(TextView tvNameDish, LinearLayout linearLayoutQuantity, TextView tvPrice, TextView tvNote){
        this.tvNameDish = tvNameDish;
        this.linearLayoutQuantity = linearLayoutQuantity;
        this.tvPrice = tvPrice;
        this.tvNote = tvNote;
    }

//    public Row(TextView tvNameDish, LinearLayout linearLayoutQuantity, TextView tvQuantity, ImageButton imgBtnIncrease, ImageButton imgBtnDecrease, TextView tvPrice, TextView tvNote){
//        this.tvNameDish = tvNameDish;
//        this.linearLayoutQuantity = linearLayoutQuantity;
//        this.tvQuantity = tvQuantity;
//        this.imgBtnDecrease = imgBtnDecrease;
//        this.imgBtnIncrease = imgBtnIncrease;
//        this.tvPrice = tvPrice;
//        this.tvNote = tvNote;
//    }

    public TextView getTvNameDish() {
        return tvNameDish;
    }

    public void setTvNameDish(TextView tvNameDish) {
        this.tvNameDish = tvNameDish;
    }

    public LinearLayout getLinearLayoutQuantity() {
        return linearLayoutQuantity;
    }

    public void setLinearLayoutQuantity(LinearLayout linearLayoutQuantity) {
        this.linearLayoutQuantity = linearLayoutQuantity;
    }

//    public TextView getTvQuantity() {
//        return tvQuantity;
//    }
//
//    public void setTvQuantity(TextView tvQuantity) {
//        this.tvQuantity = tvQuantity;
//    }
//
//    public ImageButton getImgBtnIncrease() {
//        return imgBtnIncrease;
//    }
//
//    public void setImgBtnIncrease(ImageButton imgBtnIncrease) {
//        this.imgBtnIncrease = imgBtnIncrease;
//    }
//
//    public ImageButton getImgBtnDecrease() {
//        return imgBtnDecrease;
//    }
//
//    public void setImgBtnDecrease(ImageButton imgBtnDecrease) {
//        this.imgBtnDecrease = imgBtnDecrease;
//    }

    public TextView getTvPrice() {
        return tvPrice;
    }

    public void setTvPrice(TextView tvPrice) {
        this.tvPrice = tvPrice;
    }

    public TextView getTvNote() {
        return tvNote;
    }

    public void setTvNote(TextView tvNote) {
        this.tvNote = tvNote;
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
