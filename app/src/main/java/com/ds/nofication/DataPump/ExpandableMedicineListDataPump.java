package com.ds.nofication.DataPump;

import com.ds.nofication.Models.Backend.Dosage;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.MedicineCard;

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
            drugMedicationListHashMap.put(drugMeds, drugMeds.getDosages());
        }

        return drugMedicationListHashMap;
    }
}
