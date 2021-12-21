package com.ds.nofication.Models.Backend;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Interval {
    private Days[] days;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalTime ConsumptionTime;
    public Interval(LocalDateTime start, LocalDateTime end, LocalTime consumptionTime, Days[] days){
        this.start = start;
        this.end = end;
        this.ConsumptionTime = consumptionTime;
        this.days = days;
    }

    public LocalTime getConsumptionTime() {
        return ConsumptionTime;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Days[] getDays() { return days; }
}
