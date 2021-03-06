package com.ds.nofication.Controllers;

import android.content.Context;

import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.MedicineCard;
import com.ds.nofication.Services.ReminderListCaller;

import java.text.ParseException;

public class ReminderApiController extends BaseApiController<ReminderListener> {

    /**
     * Creates reminder list caller object and calls create call
     * {@link #updateCallback(Object)} will be called if request was successfull and update all listeners
     * @param context Activity Context
     */
    public void requestReminders(Context context, String cprNumber){
        ReminderListCaller caller = new ReminderListCaller(this);
        caller.createCall(context, cprNumber);
    }

    /**
     * Adds listeners to reminder listeners
     * @param _o ReminderListener
     */
    @Override
    public void addListener(ReminderListener _o) {
        listeners.add(_o);
    }
    /**
     * Removes listeners to reminder listeners
     * @param _o ReminderListener
     */
    @Override
    public void removeListener(ReminderListener _o) {
        listeners.remove(_o);
    }

    /**
     * Updates all reminder listeners with list from the endpoint
     * @param callbackObject will be casted into correct object type
     */
    @Override
    public void updateCallback(Object callbackObject) throws ParseException {
        MedicineCard medicineCard = (MedicineCard) callbackObject;
        for(ReminderListener listener : listeners){
            listener.update(medicineCard);
        }
    }

    /**
     * Updates all reminder listeners with error message if error occurred
     * @param errorMessage Error message
     */
    @Override
    public void errorCallback(String errorMessage) {
        for(ReminderListener listener : listeners){
            listener.errorUpdate(errorMessage);
        }
    }
}
