package com.ds.nofication.Models.Backend;

import java.util.ArrayList;

public class DrugMedicineInfo {
    private String drugId;
    private ArrayList<MedicineInfo> medicineInfoList;

    public DrugMedicineInfo(String drugId, ArrayList<MedicineInfo> medicineInfoList){
        this.drugId = drugId;
        this.medicineInfoList = medicineInfoList;
    }

    public String getDrugId() {
        return drugId;
    }

    public ArrayList<MedicineInfo> getMedicineInfoList() {
        return medicineInfoList;
    }
    public ArrayList<String> getMedicineInfoListTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (MedicineInfo medInfo : medicineInfoList){
            titles.add(medInfo.getTitle());
        }
        return titles;
    }
}
