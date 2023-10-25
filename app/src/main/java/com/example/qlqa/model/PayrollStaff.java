package com.example.qlqa.model;

public class PayrollStaff {



    private long id;


    private Payroll payroll;

    private Staff staff;

    private double salary;

    private int countWork;

    public PayrollStaff() {}

    public PayrollStaff(long id, double salary) {
        this.id = id;
        this.salary = salary;
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

    public Integer getCount() {
        return countWork;
    }

    public void setCount(Integer countWork) {
        this.countWork = countWork;
    }
}
