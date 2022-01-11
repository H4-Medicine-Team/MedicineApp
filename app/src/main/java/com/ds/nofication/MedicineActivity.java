package com.ds.nofication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
    DrawerLayout drawer;
    MedicineListAdapter medicineListAdapter;
    List<DrugMedication> expandableDrugMedications;
    HashMap<DrugMedication, List<Dosage>> drugMedicationListHashMap;

    final String Tag = "MedicineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ConfigLoader cl = new ConfigLoader();
        String configVal = cl.getConfigValue(this.getBaseContext(), "api_url");

        Log.e(Tag, configVal);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        reminderController = new ReminderApiController();
        reminderController.addListener(this);
        drawer =findViewById(R.id.drawer_layout);
        //Button btn = findViewById(R.id.getdata_btn);
        //btn.setOnClickListener(this::getData);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        reminderController.requestReminders(this.getBaseContext(), "12345678912");
    }

    @Override
    public void update(MedicineCard medicineCard) {
        drugMedicationListHashMap = ExpandableMedicineListDataPump.getData(medicineCard);
        expandableDrugMedications = new ArrayList<DrugMedication>(drugMedicationListHashMap.keySet());
        medicineListAdapter = new MedicineListAdapter(this, expandableDrugMedications, drugMedicationListHashMap);
        expandableListView.setAdapter(medicineListAdapter);
    }

    /**
     * This method is called when the back button on the phone is pressed.
     * Closes the menu if open else exits the app.
     */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
    @Override
    public void errorUpdate(String errorMessage) {
        Log.e(Tag, "Error update: " + errorMessage);
    }
}