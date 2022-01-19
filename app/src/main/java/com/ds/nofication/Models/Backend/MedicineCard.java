package com.ds.nofication.Models.Backend;

import java.util.ArrayList;

public class MedicineCard {

    public MedicineCard(ArrayList<DrugMedication> drugs){
        drugMedications = drugs;
    }

    /**
     * Refers to the list of medication the patient has.
     */
    private ArrayList<DrugMedication> drugMedications;
    public ArrayList<DrugMedication> getDrugMedications(){
        return drugMedications;
    }
}
