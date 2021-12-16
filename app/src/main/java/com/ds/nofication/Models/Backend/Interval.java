package com.ds.nofication.Models.Backend;

import java.time.LocalDateTime;

public class Interval {
    private Days[] days;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime ConsumptionTime;
    public Interval(LocalDateTime start, LocalDateTime end, LocalDateTime consumptionTime, Days[] days){
        this.start = start;
        this.end = end;
        this.ConsumptionTime = consumptionTime;
        this.days = days;
    }

    public LocalDateTime getConsumptionTime() {
        return ConsumptionTime;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Days[] getDays() {
        return days;
    }
}
