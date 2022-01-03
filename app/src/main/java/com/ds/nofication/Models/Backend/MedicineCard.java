package com.ds.nofication.Models.Backend;

import java.util.ArrayList;

public class MedicineCard {

    public MedicineCard(ArrayList<DrugMedication> drugs){
        drugMedication = drugs;
    }

    /**
     * Refers to the list of medication the patient has.
     */
    private ArrayList<DrugMedication> drugMedication;
    public ArrayList<DrugMedication> getDrugMedications(){
        return drugMedication;
    }
}
