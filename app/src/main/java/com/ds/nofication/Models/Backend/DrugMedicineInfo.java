package com.ds.nofication.Models.Backend;

import java.util.ArrayList;

public class DrugMedicineInfo {
    private Drug drug;
    private ArrayList<MedicineInfo> medicineInfoList;

    public DrugMedicineInfo(Drug drug, ArrayList<MedicineInfo> medicineInfoList){
        this.drug = drug;
        this.medicineInfoList = medicineInfoList;
    }

    public Drug getDrug() {
        return drug;
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
