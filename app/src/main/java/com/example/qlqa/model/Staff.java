package com.example.qlqa.model;

import java.util.List;
import java.util.Set;

public class Staff {

    private long idStaff;

    private String nameStaff;

    private float workingHours;


    private double oDaySalary;


    private String position;

    private List<Order> order;

    private Set<TimeSheetsStaff> setTimeSheets;

    private Account account;

    public Staff() {}

    public Staff(long idStaff, String nameStaff, float workingHours, float oHourSalary, String position, Account account) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.workingHours = workingHours;
        this.oDaySalary = oHourSalary;
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

    public double getoDaySalary() {
        return oDaySalary;
    }

    public void setoDaySalary(double oDaySalary) {
        this.oDaySalary = oDaySalary;
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

    public Set<TimeSheetsStaff> getSetTimeSheets() {
        return setTimeSheets;
    }

    public void setSetTimeSheets(Set<TimeSheetsStaff> setTimeSheets) {
        this.setTimeSheets = setTimeSheets;
    }
}
