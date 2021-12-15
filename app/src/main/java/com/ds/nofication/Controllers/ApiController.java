package com.ds.nofication.Controllers;

import android.content.Context;

import com.ds.nofication.Interfaces.ReminderCallback;
import com.ds.nofication.Interfaces.ReminderListListener;
import com.ds.nofication.Models.Reminder;
import com.ds.nofication.ReminderListCaller;

import java.util.ArrayList;

public class ApiController implements ReminderCallback {
    public ArrayList<ReminderListListener> reminderListeners = new ArrayList<>();

    public void requestReminders(Context context){
        ReminderListCaller caller = new ReminderListCaller(this);
        caller.createCall(context);
    }


    @Override
    public void updateListerns(ArrayList<Reminder> reminders){
        for(ReminderListListener listener : reminderListeners){
            listener.update(reminders);
        }
    }

    @Override
    public void errorListeners(String errorMessage) {
        for(ReminderListListener listener : reminderListeners){
            listener.errorUpdate(errorMessage);
        }
    }
}
