package com.ds.nofication.Models.Backend;

import java.util.ArrayList;

public class DrugMedicineInfo {
    //The drug id
    private String drugId;

    //List of medicine info
    private ArrayList<MedicineInfo> medicineInfoList;

    /*
    Class for connecting medicine info with a drug
     */
    public DrugMedicineInfo(String drugId, ArrayList<MedicineInfo> medicineInfoList) {
        this.drugId = drugId;
        this.medicineInfoList = medicineInfoList;
    }

    /*
    Returns the drug id
     */
    public String getDrugId() {
        return drugId;
    }

    /*
    Returns the medicineInfoList
     */
    public ArrayList<MedicineInfo> getMedicineInfoList() {
        return medicineInfoList;
    }

    //Returns the titles from every MedicineInfo in the medicineInfoList
    public ArrayList<String> getMedicineInfoListTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (MedicineInfo medInfo : medicineInfoList) {
            titles.add(medInfo.getTitle());
        }
        return titles;
    }
}
