package com.ds.nofication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.ds.nofication.Adapters.MedicineListAdapter;
import com.ds.nofication.Controllers.ReminderApiController;
import com.ds.nofication.DataPump.ExpandableMedicineListDataPump;
import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.Dosage;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.MedicineCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicineActivity extends AppCompatActivity implements ReminderListener {

    ReminderApiController reminderController;
    ExpandableListView expandableListView;
    MedicineListAdapter medicineListAdapter;
    List<DrugMedication> expandableDrugMedications;
    HashMap<DrugMedication, List<Dosage>> drugMedicationListHashMap;

    final String Tag = "MedicineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        ConfigLoader cl = new ConfigLoader();
        String configVal = cl.getConfigValue(this.getBaseContext(), "api_url");

        Log.e(Tag, configVal);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        reminderController = new ReminderApiController();
        reminderController.addListener(this);

        //Button btn = findViewById(R.id.getdata_btn);
        //btn.setOnClickListener(this::getData);

        reminderController.requestReminders(this.getBaseContext());
    }

    @Override
    public void update(MedicineCard medicineCard) {
        drugMedicationListHashMap = ExpandableMedicineListDataPump.getData(medicineCard);
        expandableDrugMedications = new ArrayList<DrugMedication>(drugMedicationListHashMap.keySet());
        medicineListAdapter = new MedicineListAdapter(this, expandableDrugMedications, drugMedicationListHashMap);
        expandableListView.setAdapter(medicineListAdapter);
    }

    @Override
    public void errorUpdate(String errorMessage) {
        Log.e(Tag, "Error update: " + errorMessage);
    }
}