package com.ds.nofication.DataPump;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ds.nofication.Models.Backend.AmountType;
import com.ds.nofication.Models.Backend.BeginEndDate;
import com.ds.nofication.Models.Backend.Days;
import com.ds.nofication.Models.Backend.Dosage;
import com.ds.nofication.Models.Backend.Drug;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.Interval;
import com.ds.nofication.Models.Backend.MedicineCard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    //TODO: REMOVE AFTER MOCK
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static HashMap<DrugMedication, List<Dosage>> getData() {
        HashMap<DrugMedication, List<Dosage>> expandableListDetail = new HashMap<DrugMedication, List<Dosage>>();

        //TODO: REMOVE MOCK DATA WHEN WE HAVE A WAY TO GET DATA HERE
        ArrayList<Dosage> dosages = new ArrayList<>();
        dosages.add(new Dosage(1, AmountType.ML, new Interval(LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), new Days[] { Days.Monday })));

        DrugMedication drugMedication = new DrugMedication(dosages, new Drug("Panodil", "123"), "321", new BeginEndDate(LocalDateTime.now(), LocalDateTime.now()));
        ArrayList<DrugMedication> drugMedications = new ArrayList<>();
        drugMedications.add(drugMedication);
        MedicineCard medicineCard = new MedicineCard(drugMedications);
        for (DrugMedication drugMeds: medicineCard.getDrugMedications()) {
            expandableListDetail.put(drugMeds, drugMeds.getDosages());
        }
        return expandableListDetail;
    }
}
