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

    //TODO: LOOK INTO THIS
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        reminderController = new ReminderApiController();
        reminderController.addListener(this);

        //Button btn = findViewById(R.id.getdata_btn);
        //btn.setOnClickListener(this::getData);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        drugMedicationListHashMap = ExpandableMedicineListDataPump.getData();
        expandableDrugMedications = new ArrayList<DrugMedication>(drugMedicationListHashMap.keySet());
        medicineListAdapter = new MedicineListAdapter(this, expandableDrugMedications, drugMedicationListHashMap);
        expandableListView.setAdapter(medicineListAdapter);

    }

    public void getData(View v){
        reminderController.requestReminders(this.getBaseContext());
    }

    @Override
    public void update(ArrayList<DrugMedication> drugMedications) {
        Log.e(Tag, "size" + drugMedications.size());

        if(drugMedications.size() > 0){
            Log.e(Tag, "med id" + drugMedications.get(0).getIdentifier());
            Log.e(Tag, "drug id" + drugMedications.get(0).getDrug().getIdentifier());
        }
    }

    @Override
    public void errorUpdate(String errorMessage) {
        Log.e(Tag, errorMessage);
    }
}