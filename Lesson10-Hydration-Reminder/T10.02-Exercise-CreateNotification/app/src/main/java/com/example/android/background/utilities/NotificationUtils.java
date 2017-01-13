package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

public class NotificationUtils {

    private static final int WATER_REMINDER_NOTIFICATION_ID = 1138;
    private static final int WATER_REMINDER_PENDING_INTENT_ID = 3417;

    // TODO (7) Create a method called remindUserBecauseCharging which takes a Context.
    public static void remindUserBecauseCharging(Context context) {

        // TODO (8) In the remindUser method use NotificationCompat.Builder to create a notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);
        // TODO (9) If the build version is greater than JELLY_BEAN, set the notification's priority
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }
        // TODO (11) Get a NotificationManager, using context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        // TODO (12) Trigger the notification by calling notify on the NotificationManager.
        notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
    }

    // TODO (1) Create a helper method called contentIntent with a single parameter for a Context. It
    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        // TODO (2) Create an intent that opens up the MainActivity
        // TODO (3) Create a PendingIntent using getActivity that:
        return PendingIntent.getActivity(
                context,
                WATER_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // TODO (4) Create a helper method called largeIcon which takes in a Context as a parameter and
    private static Bitmap largeIcon(Context context) {
        // TODO (5) Get a Resources object from the context.
        Resources res = context.getResources();
        // TODO (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_local_drink_black_24px);
        return largeIcon;
    }
}
