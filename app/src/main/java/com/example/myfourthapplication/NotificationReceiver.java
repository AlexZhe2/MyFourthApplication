package com.example.myfourthapplication;

import static android.content.Context.MODE_MULTI_PROCESS;
import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.getSystemService;

import static com.example.myfourthapplication.Edit_Task_Activity.string_text_for_notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NotificationReceiver extends BroadcastReceiver {



    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";

    private static final int NOTIFY_ID = 101;
    MainActivity ma = new MainActivity();
    Edit_Task_Activity ETA = new Edit_Task_Activity();
    //String st_for_text_notif;
    private Object NotificationReceiver;
    private static final String PREF_NAME = "5";

    String main_text_for_task="";

    SharedPreferences settings;

    @Override
    public void onReceive(Context context, Intent intent) {

        ma.trueTestResiver();

        //
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        //    st_for_text_notif=ETA.getArrayValue(0);

        //   ma.look_for_Notification();

        // ma.create_Channel ();
        //  ma.trueNotification();
        //  trueNotification();

        // SharedPreferences.Editor prefEditor = settings.edit();
////time
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm"); // класс для форматирования
        SimpleDateFormat formatter_data = new SimpleDateFormat("dd.MM.yyyy"); // класс для форматирования
        //  Date date = new Date(System.currentTimeMillis());
        Date current_date_Notification = new Date(); // при создании объекта автоматом задается текущая дата
        String cur_Time_for_Notification = formatter.format(current_date_Notification);
        String cur_Data_for_Notification = formatter_data.format(current_date_Notification);

        // long long_for_cur_Time_for_Notification=0;
        System.out.println("===========formatter.format(current_date)==" + formatter.format(current_date_Notification));
        System.out.println("===========formatter.format(current_date)-2==" + cur_Time_for_Notification);
        System.out.println("===========formatter.format(current_date)-3==" + cur_Time_for_Notification);
        //  System.out.println("===========formatter.format(current_date)==" + cur_Data_for_Notification);

/////


        //    SharedPreferences settings = ETA.getSharedPreferences("PreferencesName2", Context.MODE_MULTI_PROCESS);
        SharedPreferences settings = context.getSharedPreferences("PreferencesName2", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();

        // ETA.settings = getSharedPreferences("PreferencesName", MODE_PRIVATE);

        String name = settings.getString(PREF_NAME, "не определено");
        String name2 = String.valueOf(settings.getAll());

        // String name = settings.getString(PREF_NAME,"не определено");
        System.out.println("======next from SharedPreferences notif=====" + name);
        System.out.println("======next from SharedPreferences notif ALL=====" + name2);

        ////////
        //Map<String, ?> my_array = new HashMap<String, String>(); // создание списка который
        // Map<String, ?> my_array2 = new HashMap<String, String>((Map<? extends String, ? extends String>) settings.getAll()); // создание списка который
        Map<String, ?> my_Map = settings.getAll();
        //   my_array=settings.getAll();

        //  String first = String.valueOf(my_array.get(5));
        String first = String.valueOf(my_Map.get("5"));
        System.out.println("==first== " + first);
        System.out.println("==first all== " + my_Map);
        ////////////
        //перебор мапы
        //попробовать собрать трехмерный массив и с ним работать
        for (Map.Entry<String, ?> item : my_Map.entrySet()) {

            String tempTime = item.getKey();
            String delimeter = "=";
            //String[] subStrTime = tempTime.split(delimeter, 2); //массив  разбивает на 2 части
            String[] main_subStrTime = tempTime.split(delimeter, 4); //массив  разбивает на 4 части
            System.out.println("==cur_Time_for_Notification== " + cur_Time_for_Notification);
            //  System.out.println("==subStrTime[0]== "+subStrTime[0]);
            System.out.println("==subStrTime[0]== " + main_subStrTime[1]);


            //   if (cur_Time_for_Notification==subStrTime[0]){
            // if (cur_Time_for_Notification.equals(subStrTime[0])){  // сравниваем с первым элементом массива
            if (cur_Time_for_Notification.equals(main_subStrTime[1]) && cur_Data_for_Notification.equals(main_subStrTime[2])) {  // сравниваем с первым элементом массива

                //  System.out.println("BINGO! "+"key =  " + item.getKey() + " value = " + item.getValue());
                System.out.println("BINGO! " + "key =  " + item.getKey() + " value = " + main_subStrTime[3]);
                main_text_for_task = main_subStrTime[3];
                call_Notification_builder(context,contentIntent);
                prefEditor.remove(tempTime);
                prefEditor.apply();

            }
            System.out.println("key =  " + item.getKey() + " value = " + item.getValue());


            //   System.out.printf("Key: %d  Value: %s \n", item.getKey(), item.getValue());
        }

// прописать удаление из списка событий по которым уже были уведомления
        /*  // не проверено как работает
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.remove( PREF_NAME); // удаляет из настроек значение с ключом key
        prefEditor.apply();
        */
///////
        // SharedPreferences.Editor prefEditor = settings.edit();
        //    prefEditor.remove( PREF_NAME);  //ключ, значение
        //   prefEditor.apply();

        //   System.out.println("======next from SharedPreferences notif 2====="+name2);
        //   System.out.println("======next from SharedPreferences notif====="+ETA.gg());

////

/////


            System.out.println("======Receiver=====");
/*

            NotificationCompat.Builder builder =
                    //   builder =
                    new NotificationCompat.Builder(context, CHANNEL_ID)
                            //   .setSmallIcon(R.drawable.ic_pets_black_24dp)
                            .setSmallIcon(R.drawable.ic_image_01)
                            //   .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Напоминание")
                            //   .setContentText("Пора покормить кота-222")
                            //   .setContentText(string_text_for_notification)
                            .setContentText(main_text_for_task)
                            //       .setContentText(st_for_text_notif)
                            //  .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            //   .addAction(R.drawable.R.drawable.ic_image_01, "Открыть", pendingIntent)
                            .addAction(R.drawable.ic_image_01, "Открыть", contentIntent) // кнопка действия
                            .setAutoCancel(true); // автоматически закрыть уведомление после нажатия


            NotificationManagerCompat notificationManager =
                    //   notificationManager =
                    NotificationManagerCompat.from(context);
            notificationManager.notify(NOTIFY_ID, builder.build());
*/

        }


    public void call_Notification_builder (Context context_2, PendingIntent contentIntent_2){
        System.out.println("======Receiver=====");

        NotificationCompat.Builder builder =
                //   builder =
                new NotificationCompat.Builder(context_2, CHANNEL_ID)
                        //   .setSmallIcon(R.drawable.ic_pets_black_24dp)
                        .setSmallIcon(R.drawable.ic_image_01)
                        //   .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Напоминание")
                        //   .setContentText("Пора покормить кота-222")
                        //   .setContentText(string_text_for_notification)
                        .setContentText(main_text_for_task)
                        //       .setContentText(st_for_text_notif)
                        //  .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        //   .addAction(R.drawable.R.drawable.ic_image_01, "Открыть", pendingIntent)
                        .addAction(R.drawable.ic_image_01, "Открыть приложение", contentIntent_2) // кнопка действия
                        .setAutoCancel(true); // автоматически закрыть уведомление после нажатия


        NotificationManagerCompat notificationManager =
                //   notificationManager =
                NotificationManagerCompat.from(context_2);
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
