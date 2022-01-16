package com.ds.nofication.Listeners;

import com.ds.nofication.Models.Backend.DrugMedicineInfo;
import com.ds.nofication.Models.Backend.MedicineInfo;

public interface MedicineDkListener {
    /**
     * Method that will be called on response
     * @param drugMedicineInfo Medicine info
     */
    public void update(DrugMedicineInfo drugMedicineInfo);

    /**
     * Method that will be called if error has occurred
     * @param errorMessage errorMessage
     */
    public void errorUpdate(String errorMessage);
}
