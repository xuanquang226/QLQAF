package com.example.qlqa.model;

import java.sql.Timestamp;

public class TimeSheetsStaff {


    private long id;

    private TimeSheets timeSheets;

    private Staff stafff;

    private Timestamp timestamp;

    public TimeSheetsStaff(){}

    public TimeSheetsStaff(long id, TimeSheets timeSheets, Staff stafff, Timestamp timestamp) {
        this.id = id;
        this.timeSheets = timeSheets;
        this.stafff = stafff;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TimeSheets getTimeSheets() {
        return timeSheets;
    }

    public void setTimeSheets(TimeSheets timeSheets) {
        this.timeSheets = timeSheets;
    }

    public Staff getStaff() {
        return stafff;
    }

    public void setStaff(Staff stafff) {
        this.stafff = stafff;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
