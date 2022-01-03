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

    /**
     * Separates the medicine card into hash table, based on drug medication as a key and List of dosages
     * as value
     * @param medicineCard Medicine card
     * @return Hashtable devided by Drug medication and dosages for that medication
     */
    public static HashMap<DrugMedication, List<Dosage>> getData(MedicineCard medicineCard) {

        HashMap<DrugMedication, List<Dosage>> drugMedicationListHashMap = new HashMap<DrugMedication, List<Dosage>>();

        for (DrugMedication drugMeds : medicineCard.getDrugMedications()) {
            drugMedicationListHashMap.put(drugMeds, drugMeds.getDosage());
        }

        return drugMedicationListHashMap;
    }
}
