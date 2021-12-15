package com.ds.nofication.Interfaces;

import com.ds.nofication.Models.Reminder;

import java.util.ArrayList;

public interface ReminderCallback {
     void updateListerns(ArrayList<Reminder> reminders);
     void errorListeners(String errorMessage);
}
