package com.ds.nofication.Models.Backend;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ds.nofication.DateConverter;

import java.time.LocalDateTime;

public class BeginEndDate {
    private String startDate;
    private String endDate;

    public BeginEndDate(String startDate, String endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getStartDate() {
        return DateConverter.convertStringToLocalDateTime(startDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getEndDate() {
        return DateConverter.convertStringToLocalDateTime(endDate);
    }
}
