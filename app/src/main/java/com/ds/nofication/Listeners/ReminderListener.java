package com.ds.nofication.Listeners;

import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.MedicineCard;

import java.text.ParseException;
import java.util.ArrayList;

public interface ReminderListener {
    /**
     * Method that will be called on response
     * @param medicineCard Medicine
     */
    public void update(MedicineCard medicineCard) throws ParseException;

    /**
     * Method that will be called if error has occurred
     * @param errorMessage errorMessage
     */
    public void errorUpdate(String errorMessage);
}
