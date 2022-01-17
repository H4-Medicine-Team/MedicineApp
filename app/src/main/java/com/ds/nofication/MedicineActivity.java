package com.ds.nofication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.ds.nofication.fragments.GuardianFragment;
import com.ds.nofication.fragments.MedicineCardFragment;
import com.ds.nofication.fragments.MedicineInfoFragment;
import com.ds.nofication.fragments.MedicineReorderFragment;
import com.google.android.material.navigation.NavigationView;


public class MedicineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
=======
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

    DrawerLayout drawer;
    Toolbar toolbar;
    final String Tag = "MedicineActivity";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);
        ConfigLoader cl = new ConfigLoader();
        String configVal = cl.getConfigValue(this.getBaseContext(), "api_url");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //Makes sure that medicinecard is our first fragment when loading the app
        if (savedInstanceState == null)
        {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MedicineCardFragment()).commit();
        navigationView.setCheckedItem(R.id.medicineCard);
        }
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

    /**
     *This method runs when a menu item has been clicked, it navigates to the view linked with the item.
     * @param item the menu item that has been clicked
     * @return we want this to return true since that tell our app that a menu item has been selected.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.medicineCard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MedicineCardFragment()).commit();
                toolbar.setTitle("Medicinkort");
                break;
            case R.id.medicineInfo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MedicineInfoFragment()).commit();
                toolbar.setTitle("Medicin Info");
                break;
            case R.id.reorder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MedicineReorderFragment()).commit();
                toolbar.setTitle("Genbestil");
                break;
            case R.id.guardian:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GuardianFragment()).commit();
                toolbar.setTitle("Værge");
                break;
            case R.id.settings:
                Toast.makeText(this, "Indstillinger", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}