package com.ds.nofication.Interfaces;

import com.ds.nofication.Models.Reminder;

import java.util.ArrayList;

public interface ReminderListListener {
    public void update(ArrayList<Reminder> reminders);
    public void errorUpdate(String errorMessage);
}
