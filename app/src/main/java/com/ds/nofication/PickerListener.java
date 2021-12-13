package com.ds.nofication;

import android.widget.DatePicker;
import android.widget.TimePicker;

public interface PickerListener {

    void OnTimeChanged( int hourOfDay, int minute, int sec);
    void OnDateChanged(int year, int month, int day);

}
