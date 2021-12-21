package com.ds.nofication.Controllers;

import android.content.Context;

import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.MedicineCard;
import com.ds.nofication.ReminderListCaller;

import java.util.ArrayList;

public class ReminderApiController extends BaseApiController {
    public ArrayList<ReminderListener> reminderListeners = new ArrayList<>();

    /**
     * Creates reminder list caller object and calls create call
     * {@link #updateCallback(Object)} will be called if request was successfull and update all listeners
     * @param context
     */
    public void requestReminders(Context context){
        ReminderListCaller caller = new ReminderListCaller(this);
        caller.createCall(context);
    }

    /**
     * Adds listeners to reminder listeners
     * @param _o
     */
    @Override
    public void addListener(Object _o) {
        ReminderListener rl = (ReminderListener) _o;
        reminderListeners.add(rl);
    }
    /**
     * Removes listeners to reminder listeners
     * @param _o
     */
    @Override
    public void removeListener(Object _o) {
        reminderListeners.remove(_o);
    }

    /**
     * Updates all reminder listeners with list from the endpoint
     * @param callbackObject will be casted into correct object type
     */
    @Override
    public void updateCallback(Object callbackObject) {
        MedicineCard medicineCard = (MedicineCard) callbackObject;
        for(ReminderListener listener : reminderListeners){
            listener.update(medicineCard);
        }
    }

    /**
     * Updates all reminder listeners with error message if error occurred
     * @param errorMessage
     */
    @Override
    public void errorCallback(String errorMessage) {
        for(ReminderListener listener : reminderListeners){
            listener.errorUpdate(errorMessage);
        }
    }
}
