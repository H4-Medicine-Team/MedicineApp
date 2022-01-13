package com.ds.nofication.DataPump;

import com.ds.nofication.Models.Backend.Drug;
import com.ds.nofication.Models.Backend.MedicineInfo;
import com.ds.nofication.Models.Backend.DrugMedicineInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicineInfoListDataPump {
    /**
     * Separates the medicine info view model into a hash table, based on drug as a key and List of medicine info titles
     * as value
     * @param drugMedicineInfoList a drug and a list of medicine info titles
     * @return Hashtable divided by Drug and medicine info for that drug
     */
    public static HashMap<Drug, List<String>> getSecondLevelData(ArrayList<DrugMedicineInfo> drugMedicineInfoList) {

        HashMap<Drug, List<String>> drugMedicineInfoListHashMap = new HashMap<Drug, List<String>>();

        for (DrugMedicineInfo drugMedicineInfo : drugMedicineInfoList) {
            drugMedicineInfoListHashMap.put(drugMedicineInfo.getDrug(), drugMedicineInfo.getMedicineInfoListTitles());
        }

        return drugMedicineInfoListHashMap;
    }

    public static List<String> getDrugNames(ArrayList<DrugMedicineInfo> drugMedicineInfoList){
        List<String> drugNames = new ArrayList<>();
        for (DrugMedicineInfo drugMedicineInfo : drugMedicineInfoList){
            drugNames.add(drugMedicineInfo.getDrug().getName());
        }
        return drugNames;
    }
}
