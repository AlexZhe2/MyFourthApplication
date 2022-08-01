package com.example.myfourthapplication;

import static android.content.Context.MODE_MULTI_PROCESS;
import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.getSystemService;

import static com.example.myfourthapplication.Edit_Task_Activity.string_text_for_notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationReceiver extends BroadcastReceiver {



    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";

    private static final int NOTIFY_ID = 101;
    MainActivity ma = new MainActivity();
    Edit_Task_Activity ETA = new Edit_Task_Activity();
    //String st_for_text_notif;
    private Object NotificationReceiver;
    private static final String PREF_NAME = "5";


    SharedPreferences settings;

    @Override
    public void onReceive(Context context, Intent intent) {

         ma.trueTestResiver();

    //    st_for_text_notif=ETA.getArrayValue(0);

      //   ma.look_for_Notification();

       // ma.create_Channel ();
      //  ma.trueNotification();
      //  trueNotification();




    //    SharedPreferences settings = ETA.getSharedPreferences("PreferencesName2", Context.MODE_MULTI_PROCESS);
        SharedPreferences settings = context.getSharedPreferences("PreferencesName2", Context.MODE_MULTI_PROCESS);
       // ETA.settings = getSharedPreferences("PreferencesName", MODE_PRIVATE);

        String name = settings.getString(PREF_NAME,"не определено");
       // String name2 = String.valueOf(settings.getAll());

      // String name = settings.getString(PREF_NAME,"не определено");
       System.out.println("======next from SharedPreferences notif====="+name);

      // SharedPreferences.Editor prefEditor = settings.edit();
      //    prefEditor.remove( PREF_NAME);  //ключ, значение
      //   prefEditor.apply();

    //   System.out.println("======next from SharedPreferences notif 2====="+name2);
    //   System.out.println("======next from SharedPreferences notif====="+ETA.gg());






        System.out.println("======Receiver=====");

        NotificationCompat.Builder builder =
                //   builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        //   .setSmallIcon(R.drawable.ic_pets_black_24dp)
                        .setSmallIcon(R.drawable.ic_image_01)
                        //   .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Напоминание")
                     //   .setContentText("Пора покормить кота-222")
                        .setContentText(string_text_for_notification)
                 //       .setContentText(st_for_text_notif)
                        //  .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager =
                //   notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFY_ID, builder.build());

    }

/*
    int q = 0;

    public void look_for_Notification (){

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        //  Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы
        Cursor query = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы


       // k = query.getCount();

        if (q == 0) {

            System.out.println("============= if(j==0) " + q);
            ///
            while (query.moveToNext()) {


                int id_for_object = query.getInt(0);
                String name = query.getString(1);
                long data_long = query.getLong(2);
                boolean value_checkBox_from_DB = Boolean.parseBoolean(query.getString(3));//2
                long done_data_fact = query.getLong(4);
                long data_long_time = query.getLong(5);

            }
        }
    }
    */

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
