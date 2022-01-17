package com.ds.nofication.Controllers;

import android.content.Context;

import com.ds.nofication.Callers.MedicineDkCaller;
import com.ds.nofication.Listeners.MedicineDkListener;
import com.ds.nofication.Models.Backend.DrugMedicineInfo;
import com.ds.nofication.Models.Backend.MedicineDkDTO;
import com.ds.nofication.Models.Backend.MedicineDkWithIdDTO;
import com.ds.nofication.Models.Backend.MedicineInfo;

import java.util.ArrayList;
import java.util.List;

public class MedicineDkApiController extends BaseApiController<MedicineDkListener> {

    /**
     * Creates medicineDkCaller object and calls create call
     * {@link #updateCallback(Object)} will be called if request was successfull and update all listeners
     * @param context Activity Context
     */
    public void requestGetMedicine(Context context, String identifier){
        MedicineDkCaller caller = new MedicineDkCaller(this);
        caller.createCall(context, identifier);
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
        MedicineDkWithIdDTO medicineDkWithIdDTO = (MedicineDkWithIdDTO) callbackObject;
        ArrayList<MedicineInfo> medicineInfos = new ArrayList<>();
        for (MedicineDkDTO medDkDto: medicineDkWithIdDTO.getMedicineDTOs)
        {
            if (medDkDto.getHtmlData().length != 0)
                medicineInfos.add(new MedicineInfo(medDkDto.getHtmlData(), medDkDto.getTitle()));
        }
        DrugMedicineInfo drugMedicineInfo = new DrugMedicineInfo(medicineDkWithIdDTO.identifier, medicineInfos);
        for(MedicineDkListener listener : listeners){
            listener.update(drugMedicineInfo);
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