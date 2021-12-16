package com.ds.nofication.Models.Backend;

import java.util.ArrayList;

public class MedicineCard {

    public MedicineCard(ArrayList<DrugMedication> drugs){
        drugMedication = drugs;
    }

    private ArrayList<DrugMedication> drugMedication;
    public ArrayList<DrugMedication> getDrugMedication(){
        return drugMedication;
    }
}
