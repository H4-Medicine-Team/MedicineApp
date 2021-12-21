package com.ds.nofication.Models.Backend;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DrugMedication {
    private ArrayList<Dosage> dosage;
    private Drug drug;
    private String identifier;
    private BeginEndDate beginEndDate;

    public DrugMedication(ArrayList<Dosage> dosages, Drug drug, String identifier, BeginEndDate beginEndDate){
        this.dosage = dosages;
        this.drug = drug;
        this.identifier = identifier;
        this.beginEndDate = beginEndDate;
    }

    public ArrayList<Dosage> getDosage() { return dosage; }

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

        for(int i = 0; i < dosage.size(); i++){
            if(newest == null)
                newest = dosage.get(i);

            double timeToTake = ChronoUnit.SECONDS.between(dosage.get(i).getInterval().getConsumptionTime(), LocalDateTime.now());

            if(timeToTake < timeBefore){
                newest = dosage.get(i);
                timeBefore = timeToTake;
            }
        }
        return newest;
    }
}
