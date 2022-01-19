package com.ds.nofication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationPublisher  extends BroadcastReceiver {


    /**
     * Waiting for the invoke from AlarmClock manager, when triggered it will start notify user
     * @param context the application context
     * @param intent Intent with notification
     */
    public void onReceive(Context context, Intent intent) {
        //getting notification manager from Context
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Gets the stored notification from the intent
        Notification notification = intent.getParcelableExtra(context.getString(R.string.Notification));
        //Getting the notification ID
        int id = intent.getIntExtra(context.getString(R.string.NotificationId), 0);
        //Start the notification with the chosen id, (ID is used when someone want to interact with the notification)
        notificationManager.notify(id, notification);
        //Running the sound for notification
        playNotificationSound(context);
    }

    /**
     * Play default alarm sound
     * @param context the context for retrieval of Alarm soundss
     */
    public void playNotificationSound(Context context) {
        try {
            //Getting the default alarm tone uri
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            //Getting the default ringtone from application context
            Ringtone r = RingtoneManager.getRingtone(context, defaultSoundUri);
            //Playing the alarm
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}