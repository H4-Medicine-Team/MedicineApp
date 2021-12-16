package com.ds.nofication.Models.Backend;

import java.time.LocalDateTime;

public class BeginEndDate {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    public BeginEndDate(LocalDateTime startDate, LocalDateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
