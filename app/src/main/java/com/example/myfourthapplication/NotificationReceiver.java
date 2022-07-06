package com.example.myfourthapplication;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationReceiver extends BroadcastReceiver {

    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";

    private static final int NOTIFY_ID = 101;
    MainActivity ma = new MainActivity();
    private Object NotificationReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {

         ma.trueTestResiver();
       // ma.create_Channel ();
      //  ma.trueNotification();
      //  trueNotification();

        System.out.println("======Receiver=====");

        NotificationCompat.Builder builder =
                //   builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        //   .setSmallIcon(R.drawable.ic_pets_black_24dp)
                        .setSmallIcon(R.drawable.ic_image_01)
                        //   .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Напоминание")
                        .setContentText("Пора покормить кота")
                        //  .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager =
                //   notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFY_ID, builder.build());

    }



/*

    public void trueNotification(){

        NotificationCompat.Builder builder =
                //   builder =
                new NotificationCompat.Builder(NotificationReceiver.this, CHANNEL_ID)
                        //   .setSmallIcon(R.drawable.ic_pets_black_24dp)
                        .setSmallIcon(R.drawable.ic_image_01)
                        //   .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Напоминание")
                        .setContentText("Пора покормить кота")
                        //  .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager =
                //   notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(NOTIFY_ID, builder.build());

    }
*/


}
