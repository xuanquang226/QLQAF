package com.example.qlqa.model;

import java.sql.Timestamp;
import java.util.Set;

public class Payroll {

    private long id;

//	@Autowired
//	@Column(name = "allowance")
//	private Allowance aw;
//
//	@Autowired
//	@Column(name = "tax")
//	private Tax tax;




    private Timestamp dateCreatePayroll;


    private Set<PayrollStaff> setStaff;


    public Payroll() {}


//	public Payroll(long id, Allowance aw, Tax tax, double salary, String dateCreatePayroll) {
//		this.id = id;
//		this.aw = aw;
//		this.tax = tax;
//		this.salary = salary;
//		this.dateCreatePayroll = dateCreatePayroll;
//	}

    public Payroll(long id, Timestamp dateCreatePayroll) {
        this.id = id;
        this.dateCreatePayroll = dateCreatePayroll;
    }


    public long getId() {
        return id;
    }


//	public void setId(long id) {
//		this.id = id;
//	}
//
//
//	public Allowance getAw() {
//		return aw;
//	}
//
//
//	public void setAw(Allowance aw) {
//		this.aw = aw;
//	}
//
//
//	public Tax getTax() {
//		return tax;
//	}
//
//
//	public void setTax(Tax tax) {
//		this.tax = tax;
//	}



    public Timestamp getDateCreatePayroll() {
        return dateCreatePayroll;
    }


    public void setDateCreatePayroll(Timestamp dateCreatePayroll) {
        this.dateCreatePayroll = dateCreatePayroll;
    }


    public Set<PayrollStaff> getSetStaff() {
        return setStaff;
    }


    public void setSetStaff(Set<PayrollStaff> setStaff) {
        this.setStaff = setStaff;
    }

}
