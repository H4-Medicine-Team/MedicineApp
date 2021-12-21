package com.ds.nofication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime convertStringToLocalDateTime(String toConvert){
        Date date = convertStringToDate(toConvert);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalTime convertStringToLocalTime(String toConvert){
        Date date = convertStringToDate(toConvert);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  static  Date convertStringToDate(String toConvert){
        OffsetDateTime odt = OffsetDateTime.parse(toConvert);
        Instant instant = odt.toInstant();
        java.util.Date date = Date.from(instant);

        return date;
    }
}
