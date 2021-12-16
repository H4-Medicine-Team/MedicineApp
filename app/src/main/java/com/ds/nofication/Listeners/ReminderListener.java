package com.ds.nofication.Listeners;

import com.ds.nofication.Models.Backend.DrugMedication;

import java.util.ArrayList;

public interface ReminderListener {
    public void update(ArrayList<DrugMedication> drugMedications);
    public void errorUpdate(String errorMessage);
}
