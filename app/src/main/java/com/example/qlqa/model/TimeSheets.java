package com.example.qlqa.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TimeSheets {

    private long id;

    private Timestamp dateTimeKeeping;

    private Set<TimeSheetsStaff> setTimeSheetsStaff;

    public TimeSheets() {}

    public TimeSheets(long id, Timestamp dateTimeKeeping) {
        this.id = id;
        this.dateTimeKeeping = dateTimeKeeping;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateTimeKeeping() {
        return dateTimeKeeping;
    }

    public void setDateTimeKeeping(Timestamp dateTimeKeeping) {
        this.dateTimeKeeping = dateTimeKeeping;
    }

    public Set<TimeSheetsStaff> getSetTimeSheetsStaff() {
        return setTimeSheetsStaff;
    }

    public void setSetTimeSheetsStaff(Set<TimeSheetsStaff> setTimeSheetsStaff) {
        this.setTimeSheetsStaff = setTimeSheetsStaff;
    }

}
