package com.ds.nofication.Models.Backend;

import java.util.ArrayList;

public class DrugMedication {
    private ArrayList<Dosage> dosages;
    private Drug drug;
    private String identifier;
    private BeginEndDate beginEndDate;

    public DrugMedication(ArrayList<Dosage> dosages, Drug drug, String identifier, BeginEndDate beginEndDate){
        this.dosages = dosages;
        this.drug = drug;
        this.identifier = identifier;
        this.beginEndDate = beginEndDate;
    }

    public ArrayList<Dosage> getDosages() {
        return dosages;
    }

    public Drug getDrug() {
        return drug;
    }

    public String getIdentifier() {
        return identifier;
    }

    public BeginEndDate getBeginEndDate() {
        return beginEndDate;
    }
}
