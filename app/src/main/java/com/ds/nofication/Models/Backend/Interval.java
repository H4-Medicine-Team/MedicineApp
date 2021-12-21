package com.ds.nofication.Models.Backend;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ds.nofication.DateConverter;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Interval {
    private Days[] days;
    private String start;
    private String end;
    private String consumptionTime;
    public Interval(String start, String end, String consumptionTime, Days[] days){
        this.start = start;
        this.end = end;
        this.consumptionTime = consumptionTime;
        this.days = days;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalTime getConsumptionTime() {
        return DateConverter.convertStringToLocalTime(consumptionTime);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getStart() {
        return DateConverter.convertStringToLocalDateTime(start);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getEnd() {
        return DateConverter.convertStringToLocalDateTime(end);
    }

    public Days[] getDays() { return days; }
}
