package com.ds.nofication.Controllers;

import android.content.Context;

import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.ReminderListCaller;

import java.util.ArrayList;

public class ReminderApiController extends BaseApiController {
    public ArrayList<ReminderListener> reminderListeners = new ArrayList<>();

    public void requestReminders(Context context){
        ReminderListCaller caller = new ReminderListCaller(this);
        caller.createCall(context);
    }

    @Override
    public void addListener(Object _o) {
        ReminderListener r = (ReminderListener) _o;
        reminderListeners.add(r);
    }

    @Override
    public void removeListener(Object _o) {
        reminderListeners.remove(_o);
    }

    @Override
    public void updateCallback(Object update) {
        ArrayList<DrugMedication> r = (ArrayList<DrugMedication>)update;
        for(ReminderListener listener : reminderListeners){
            listener.update(r);
        }
    }

    @Override
    public void errorCallback(String errorMessage) {
        for(ReminderListener listener : reminderListeners){
            listener.errorUpdate(errorMessage);
        }
    }
}
