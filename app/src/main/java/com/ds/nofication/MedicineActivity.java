package com.ds.nofication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ds.nofication.Controllers.ReminderApiController;
import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.DrugMedication;

import java.util.ArrayList;

public class MedicineActivity extends AppCompatActivity implements ReminderListener {

    ReminderApiController reminderController;
    final String Tag = "MedicineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine2);

        reminderController = new ReminderApiController();
        reminderController.addListener(this);

        Button btn = findViewById(R.id.getdata_btn);
        btn.setOnClickListener(this::getData);
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
            reminderController.removeListener(this);
        }
    }

    @Override
    public void errorUpdate(String errorMessage) {

    }
}