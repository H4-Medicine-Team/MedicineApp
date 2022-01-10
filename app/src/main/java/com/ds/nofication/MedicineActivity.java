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

        //Button btn = findViewById(R.id.getdata_btn);
        //btn.setOnClickListener(this::getData);

        reminderController.requestReminders(this.getBaseContext(), "12345678912");


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void update(MedicineCard medicineCard) {
        drugMedicationListHashMap = ExpandableMedicineListDataPump.getData(medicineCard);
        expandableDrugMedications = new ArrayList<DrugMedication>(drugMedicationListHashMap.keySet());
        medicineListAdapter = new MedicineListAdapter(this, expandableDrugMedications, drugMedicationListHashMap);
        expandableListView.setAdapter(medicineListAdapter);


//        for (int i = 0; i < medicineCard.getDrugMedications().size(); i++) {
//
//
//           LocalTime time = medicineCard.getDrugMedications().get(i).getNextDosage().getInterval().getConsumptionTime();
//           int curday = LocalDate.now().getDayOfWeek().ordinal();
//           Days[] intakeDays = medicineCard.getDrugMedications().get(i).getNextDosage().getInterval().getDays();
//           Optional<Days> intakeDay = Arrays.stream(intakeDays).filter(o ->  o != null && o.ordinal() == curday).findFirst();
//
//        if (intakeDay != null){
//
//        }
//        else{
//
//        }
         // Arrays.stream(medicineCard.getDrugMedications().get(i).getNextDosage().getInterval().getDays()).filter(n -> n.name() ==  LocalDate.now().getDayOfWeek().name()).findFirst();


//                long num = 22;
//                scheduleNotification(
//                        createNotification(
//                                getResources().getString(R.string.channel_Medicine),
//                                getResources().getString(R.string.channel_Medicine),
//                                getResources().getString(R.string.channel_Medicine),
//                                NotificationCompat.PRIORITY_HIGH, i), num, i);
       // }
    }

    public static long getDateDiff(String oldDate, String newDate) {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public void errorUpdate(String errorMessage) {
        Log.e(Tag, "Error update: " + errorMessage);
    }



    private void scheduleNotification(Notification notification, long delay, int notificationID) {



        //Intent, NotificationPublisher when time has come
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        //Putting some id's in so we know which one to broadcast (it's id and the notification object)
        notificationIntent.putExtra(getString(R.string.NotificationId), notificationID);
        notificationIntent.putExtra(getString(R.string.Notification), notification);
        //creating a pending request
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Time until broadcasting event
        long futureInMillis = SystemClock.elapsedRealtime() + delay;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        //  alarmManager.setRepeating();
    }

    private Notification createNotification(String title, String content, String channelId, int priority, int notificationID) {
//        Intent intent = new Intent(this, NotifyActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        Bundle extras = new Bundle();
//        extras.putString(NotifyActivity.notify_title, title);
//        extras.putString(NotifyActivity.notify_content, content);
//        intent.putExtras(extras);
//        intent.setAction(Intent.ACTION_VIEW);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), notificationID, intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.drawable.ic_notifications_active)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setAutoCancel(true)
                        .setLights(Color.BLUE, 500, 500)
                        .setVibrate(new long[]{500, 500, 500})
                        .setPriority(priority)
                        .setContentTitle(title)
                        .setContentText(content)
                        //.setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        // Since android Oreo notification channel is needed.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MedicineActivity.this);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    channelId,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
        }
        Notification notification = notificationBuilder.build();
        return notification;
    }

}