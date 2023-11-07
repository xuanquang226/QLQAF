package com.example.qlqa.model;

public class PayrollStaff {



    private long id;


    private Payroll payroll;

    private Staff staff;

    private double salary;

    private int count;

    public PayrollStaff() {}

    public PayrollStaff(long id, double salary, int count) {
        this.id = id;
        this.salary = salary;
        this.count = count;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int countWork) {
        this.count = count;
    }
}
