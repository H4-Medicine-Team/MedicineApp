package com.ds.nofication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private ArrayList<PickerListener> listeners = new ArrayList();


    public void addListener(PickerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PickerListener listener) {
        listeners.remove(listener);
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this,  hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        //(hourOfDay - LocalTime.now().getHour()), ( minute - LocalTime.now().getMinute())


        for (PickerListener l : listeners){
            l.OnTimeChanged(hourOfDay, minute, 0);
        }
    }
}


