package com.ds.nofication;

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
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    /**
     * Calculate time between 2 dates returning time in milliseconds
     *
     * @param newDate the newest date
     * @return
     */
    public long getDateDiff(Calendar newDate) {
        try {
            return (newDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Loads value from
     *
     * @param context        Activity context
     * @param notificationID id for broadcast receiver
     */
    public void startNotification(Context context, int notificationID, long delay) {
        try {
            scheduleNotification(
                    createNotification(
                            context,
                            context.getResources().getString(R.string.channel_Medicine),
                            context.getString(R.string.channel_Medicine),
                            context.getResources().getString(R.string.channel_Medicine),
                            NotificationCompat.PRIORITY_HIGH), delay, notificationID, context);
        } catch (Exception ex) {

        }
    }

    /**
     * Using android inbuilt alarm clock to schedule notification.
     *
     * @param notification   Notification to show
     * @param delay          wait time in milliseconds
     * @param notificationID ID for the notification
     * @param context        The activity context
     */
    private void scheduleNotification(Notification notification, long delay, int notificationID, Context context) {
        //Intent, NotificationPublisher when time has come
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        //Putting some id's in so we know which one to broadcast (it's id and the notification object)
        notificationIntent.putExtra(context.getString(R.string.NotificationId), notificationID);
        notificationIntent.putExtra(context.getString(R.string.Notification), notification);
        //creating a pending request
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Time until broadcasting event
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        //Getting the inbuilt alarm service context
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //Setting the alarm realtime converted to  Milliseconds, parsing pendingIntent (the notification)
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    /**
     * Creates an notification that contains ringtone & vibration level
     *
     * @param context   the activity context
     * @param title     The title of the notification
     * @param content   the content inside the notification
     * @param channelId the channel id, broadcast will look for this
     * @param priority  the priority towards other notification
     * @return
     */
    private Notification createNotification(Context context, String title, String content, String channelId, int priority) {
        //Builds the notification icon, vibration level, priority
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context.getApplicationContext(), channelId)
                        .setSmallIcon(R.drawable.ic_notifications_active)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                        .setAutoCancel(true)
                        .setLights(Color.BLUE, 500, 500)
                        .setVibrate(new long[]{500, 500, 500})
                        .setPriority(priority)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        // Since android Oreo notification channel is needed.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    channelId,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
        }
        //Final build of the notification.
        Notification notification = notificationBuilder.build();
        return notification;
    }
}
