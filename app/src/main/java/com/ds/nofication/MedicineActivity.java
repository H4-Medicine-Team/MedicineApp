package com.ds.nofication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.ds.nofication.Adapters.MedicineAdapter;
import com.ds.nofication.Controllers.ReminderApiController;
import com.ds.nofication.DataPump.ExpandableListDataPump;
import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.Dosage;
import com.ds.nofication.Models.Backend.DrugMedication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicineActivity extends AppCompatActivity implements ReminderListener {

    ReminderApiController reminderController;
    ExpandableListView expandableListView;
    MedicineAdapter medicineListAdapter;
    List<DrugMedication> expandableDrugMedication;
    HashMap<DrugMedication, List<Dosage>> expandableListDetail;
    final String Tag = "MedicineActivity";

    //TODO: LOOK INTO THIS
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine2);

        reminderController = new ReminderApiController();
        reminderController.addListener(this);

        //Button btn = findViewById(R.id.getdata_btn);
        //btn.setOnClickListener(this::getData);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableDrugMedication = new ArrayList<DrugMedication>(expandableListDetail.keySet());
        medicineListAdapter = new MedicineAdapter(this, expandableDrugMedication, expandableListDetail);
        expandableListView.setAdapter(medicineListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableDrugMedication.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableDrugMedication.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableDrugMedication.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableDrugMedication.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

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