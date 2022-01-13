package com.ds.nofication.Controllers;

import android.content.Context;

import com.ds.nofication.Callers.MedicineDkCaller;
import com.ds.nofication.Listeners.MedicineDkListener;
import com.ds.nofication.Models.Backend.MedicineDkDTO;
import com.ds.nofication.Models.Backend.MedicineInfo;

import java.util.ArrayList;
import java.util.List;

public class MedicineDkApiController extends BaseApiController<MedicineDkListener> {

    /**
     * Creates medicineDkCaller object and calls create call
     * {@link #updateCallback(Object)} will be called if request was successfull and update all listeners
     * @param context Activity Context
     */
    public void requestGetMedicine(Context context, String drugId){
        MedicineDkCaller caller = new MedicineDkCaller(this);
        caller.createCall(context, drugId);
    }


    /**
     * Adds listeners to MedicineDk listeners
     * @param _o MedicineDkListener
     */
    @Override
    public void addListener(Object _o) {
        MedicineDkListener mDkl = (MedicineDkListener) _o;
        listeners.add(mDkl);
    }
    /**
     * Removes listeners from MedicineDk listeners
     * @param _o MedicineDkListener
     */
    @Override
    public void removeListener(Object _o) {
        listeners.remove(_o);
    }

    /**
     * Updates all MedicineDK listeners with list from the endpoint
     * @param callbackObject will be casted into correct object type
     */
    @Override
    public void updateCallback(Object callbackObject) {
        MedicineDkDTO medicineDkDTO = (MedicineDkDTO) callbackObject;
        for(MedicineDkListener listener : listeners){
            listener.update(medicineDkDTO);
        }
    }

    /**
     * Updates all MedicineDk listeners with error message if error occurred
     * @param errorMessage Error message
     */
    @Override
    public void errorCallback(String errorMessage) {
        for(MedicineDkListener listener : listeners){
            listener.errorUpdate(errorMessage);
        }
    }
}