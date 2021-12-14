package com.ds.nofication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements PickerListener {

    private long totalTime = 0;
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int min = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button medicineBtn = findViewById(R.id.medicine_page_btn);
        medicineBtn.setOnClickListener(this::openMedicineActivity);

    }

    public void openMedicineActivity(View v){
        Intent intent = new Intent(this, MedicineActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_5:

                //scheduleNotification(getNotification("5 second delay"), 5000);
                scheduleNotification(
                        createNotification(
                                getResources().getString(R.string.channel_Medicine),
                                getResources().getString(R.string.Medicine_content),
                                getResources().getString(R.string.channel_Medicine),
                                NotificationCompat.PRIORITY_HIGH, 100), 5000);
                return true;
            case R.id.action_picker:
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.addListener(this);
                newFragment.show(getSupportFragmentManager(), "datePicker");

                DatePickerFragment newFragment2 = new DatePickerFragment();
                newFragment2.addListener(this);
                newFragment2.show(getSupportFragmentManager(), "datePicker");

                return true;
           /* case R.id.action_30:
                scheduleNotification(
                        createNotification(
                                getResources().getString(R.string.channel_Medicine),
                                getResources().getString(R.string.Medicine_content),
                                getResources().getString(R.string.channel_Medicine),
                                NotificationCompat.PRIORITY_HIGH, 100),30000);
                return true;
            case R.id.action_100:
                scheduleNotification(
                        createNotification(
                                getResources().getString(R.string.channel_Medicine),
                                getResources().getString(R.string.Medicine_content),
                                getResources().getString(R.string.channel_Medicine),
                                NotificationCompat.PRIORITY_HIGH, 100),60000);
                return  true;
            case R.id.action_200:
                scheduleNotification(
                        createNotification(
                                getResources().getString(R.string.channel_Medicine),
                                getResources().getString(R.string.Medicine_content),
                                getResources().getString(R.string.channel_Medicine),
                                NotificationCompat.PRIORITY_HIGH, 100),120000);
                return  true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void scheduleNotification(Notification notification, long delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        //  alarmManager.setRepeating();
    }

    private Notification createNotification(String title, String content, String channedId, int priorty, int notificationID) {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), channedId)
                        .setSmallIcon(R.drawable.ic_notifications_active)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setAutoCancel(true)
                        .setLights(Color.BLUE, 500, 500)
                        .setVibrate(new long[]{500, 500, 500})
                        .setPriority(priorty)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);


        // Since android Oreo notification channel is needed.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channedId,
                    channedId,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
        }
        Notification notification = notificationBuilder.build();
        return notification;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onButtonRemindSelectedClicked(View view) {
        Calendar calendar = Calendar.getInstance();
        Calendar curCal = Calendar.getInstance();
        int m = LocalDate.now().getMonthValue() - 1;
        curCal.set(
                LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1,
                LocalDate.now().getDayOfMonth(),
                LocalTime.now().getHour(),
                LocalTime.now().getMinute(),
                LocalTime.now().getSecond());

        calendar.set(year,
                month,
                day,
                hour,
                min,
                0);

        long cur = curCal.getTimeInMillis();
        long old = calendar.getTimeInMillis();
        totalTime =   old - cur;

        if (totalTime > 0) {
            scheduleNotification(
                    createNotification(
                            getResources().getString(R.string.channel_Medicine),
                            getResources().getString(R.string.Medicine_content),
                            getResources().getString(R.string.channel_Medicine),
                            NotificationCompat.PRIORITY_HIGH, 100), totalTime);
            ((Button) findViewById(R.id.buttonRemind)).setEnabled(false);

            ((TextView) findViewById(R.id.DebugLabel)).setText(
                    "Remind time \n" +
                            year + "-" + (month + 1) + "-" + day +
                            "\n" +
                            hour + ":" + min
            );
        }
    }

    @Override
    public void OnTimeChanged(int hourOfDay, int minute, int sec) {
        if (hourOfDay > 0 || minute > 0) {
            this.hour = hourOfDay;
            this.min = minute;
            ((Button) findViewById(R.id.buttonRemind)).setEnabled(true);
        }
    }

    @Override
    public void OnDateChanged(int year, int month, int day) {

        this.year = year;
        this.month = month;
        this.day = day;
    }
}