package com.example.qlqa.model;

import java.util.List;

public class Staff {

    private long idStaff;

    private String nameStaff;

    private float workingHours;


    private float oHourSalary;


    private String position;

    private List<Order> order;


    private Account account;

    public Staff() {}

    public Staff(long idStaff, String nameStaff, float workingHours, float oHourSalary, String position, Account account) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.workingHours = workingHours;
        this.oHourSalary = oHourSalary;
        this.position = position;
        this.account = account;
    }

    public long getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(long idStaff) {
        this.idStaff = idStaff;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    public float getoHourSalary() {
        return oHourSalary;
    }

    public void setoHourSalary(float oHourSalary) {
        this.oHourSalary = oHourSalary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Order> getOrder() {
        return order;
    }
}
