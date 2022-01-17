package com.ds.nofication.Models.Backend;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DrugMedication {
    /**
     * The list of dosages for this medication.
     */
    private ArrayList<Dosage> dosages;

    /**
     * The description of the drug.
     */
    private Drug drug;

    /**
     * The unique id of the medication.
     */
    private String identifier;

    /**
     * When the medicine was starting to be issued, and when the medication is ending.
     */
    private BeginEndDate beginEndDate;

    public DrugMedication(ArrayList<Dosage> dosages, Drug drug, String identifier, BeginEndDate beginEndDate){
        this.dosages = dosages;
        this.drug = drug;
        this.identifier = identifier;
        this.beginEndDate = beginEndDate;
    }

    public ArrayList<Dosage> getDosages() { return dosages; }

    public Drug getDrug() {
        return drug;
    }

    public String getIdentifier() {
        return identifier;
    }

    public BeginEndDate getBeginEndDate() {
        return beginEndDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Dosage getNextDosage(){

        Dosage newest = null;
        double timeBefore = 0;

        for(int i = 0; i < dosages.size(); i++){
            if(newest == null)
                newest = dosages.get(i);

            double timeToTake = ChronoUnit.SECONDS.between(dosages.get(i).getInterval().getConsumptionTime(), LocalDateTime.now());

            if(timeToTake < timeBefore){
                newest = dosages.get(i);
                timeBefore = timeToTake;
            }
        }
        return newest;
    }
}
