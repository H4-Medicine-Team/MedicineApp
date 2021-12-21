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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableMedicineListDataPump {
    //TODO: REMOVE AFTER MOCK
    @RequiresApi(api = Build.VERSION_CODES.O)
    //
    public static HashMap<DrugMedication, List<Dosage>> getData() {
        HashMap<DrugMedication, List<Dosage>> drugMedicationListHashMap = new HashMap<DrugMedication, List<Dosage>>();

        //TODO: REMOVE MOCK DATA WHEN WE HAVE A WAY TO GET DATA HERE
        ArrayList<Dosage> dosages = new ArrayList<>();
        ArrayList<Dosage> dosages1 = new ArrayList<>();
        dosages.add(new Dosage(1, AmountType.ML, new Interval(LocalDateTime.now(), LocalDateTime.now().plusDays(2), LocalTime.now().withHour(18), new Days[] { Days.Monday, Days.Thursday })));
        dosages.add(new Dosage(2, AmountType.ML, new Interval(LocalDateTime.now(), LocalDateTime.now().plusDays(3), LocalTime.now().withHour(12), new Days[] { Days.Monday })));
        dosages1.add(new Dosage(1, AmountType.ML, new Interval(LocalDateTime.now(), LocalDateTime.now().plusHours(3), LocalTime.now().withHour(9).withMinute(15), new Days[] { Days.Monday })));

        DrugMedication drugMedication = new DrugMedication(dosages, new Drug("Panodil", "123"), "321", new BeginEndDate(LocalDateTime.now(), LocalDateTime.now()));
        DrugMedication drugMedication1 = new DrugMedication(dosages1, new Drug("Ipren", "1234"), "4321", new BeginEndDate(LocalDateTime.now(), LocalDateTime.now()));
        ArrayList<DrugMedication> drugMedications = new ArrayList<>();
        drugMedications.add(drugMedication);
        drugMedications.add(drugMedication1);
        MedicineCard medicineCard = new MedicineCard(drugMedications);

        for (DrugMedication drugMeds: medicineCard.getDrugMedications()) {
            drugMedicationListHashMap.put(drugMeds, drugMeds.getDosage());
        }
        return drugMedicationListHashMap;
    }
}
