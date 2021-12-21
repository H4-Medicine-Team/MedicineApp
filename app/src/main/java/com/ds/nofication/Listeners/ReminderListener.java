package com.ds.nofication.Listeners;

import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.MedicineCard;

import java.util.ArrayList;

public interface ReminderListener {
    public void update(MedicineCard medicineCard);
    public void errorUpdate(String errorMessage);
}
