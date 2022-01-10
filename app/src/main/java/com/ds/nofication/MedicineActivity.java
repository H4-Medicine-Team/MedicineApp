package com.ds.nofication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.ds.nofication.Adapters.MedicineListAdapter;
import com.ds.nofication.Controllers.ReminderApiController;
import com.ds.nofication.DataPump.ExpandableMedicineListDataPump;
import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.Days;
import com.ds.nofication.Models.Backend.Dosage;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.MedicineCard;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class MedicineActivity extends AppCompatActivity implements ReminderListener {
    String id = "my_channel_01";
    ReminderApiController reminderController;
    ExpandableListView expandableListView;
    MedicineListAdapter medicineListAdapter;
    List<DrugMedication> expandableDrugMedications;
    HashMap<DrugMedication, List<Dosage>> drugMedicationListHashMap;

    final String Tag = "MedicineActivity";

    @RequiresApi(api = Build.VERSION_CODES.M)
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

        reminderController.requestReminders(this.getBaseContext(), "12345678912");

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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