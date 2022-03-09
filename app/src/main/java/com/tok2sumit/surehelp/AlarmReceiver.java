package com.tok2sumit.surehelp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "SAMPLE_CHANNEL";
    @Override
    public void onReceive(Context context, Intent intent) {
//        get id & message from intent
        int notificationid = intent.getIntExtra("notificataionid",100);
        String message = intent.getStringExtra("message");

//        call Med_Remainder when notification is tapped.
        Intent mainIntent = new Intent(context,Med_Remainder_Activity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(
                context,100,mainIntent,PendingIntent.FLAG_UPDATE_CURRENT
        );

//        Notification Manager
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService((Context.NOTIFICATION_SERVICE));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            for api 26 and above
            CharSequence channel_name = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,channel_name,importance);
            notificationManager.createNotificationChannel(channel);
        }

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


//        Prepare Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("SureHelp Notification")
                .setContentText(message)
                .setSound(soundUri)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

//        Notify
        notificationManager.notify(notificationid,builder.build());



//        Vibrating the phone when the alarm starts.
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
//            v.vibrate(10000);
        }


}
