package com.ds.nofication.Listeners;

import com.ds.nofication.Models.Reminder;

import java.util.ArrayList;

public interface ReminderListener {
    public void update(ArrayList<Reminder> reminders);
    public void errorUpdate(String errorMessage);
}
