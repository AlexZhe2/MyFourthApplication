package com.example.myfourthapplication;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class Edit_Task_Activity extends AppCompatActivity {

    private Button Button_01;
    private EditText EditText_task_01;
    private EditText EditText_task_02;
    private EditText EditText_task_03;

  //  private CheckBox CheckBox_task_01;
  private LinearLayout LinearLayout_Edit_subtask_01;

    static String string_text_from_task;
    static String string_text_from_data;
    static String string_text_from_data_time;
    static long id_from_task;

    static String string_text_for_notification;

    String st_data_notif ="";
    String st_time_notif ="";
    String st_task_notif="";

    boolean value_of_main_checkBox = false;
    String checkBox_notif= String.valueOf(value_of_main_checkBox);


    Calendar calendar = Calendar.getInstance();


    // LinearLayout_Edit_scroll_02_xml

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_task);

        Button_01 = findViewById(R.id.button_edit_task_01);
        EditText_task_01 = findViewById(R.id.EditText_edit_task_01_xml);
        EditText_task_02 = findViewById(R.id.EditText_edit_task_data_02_xml);
        EditText_task_03 = findViewById(R.id.EditText_edit_task_data_04_xml);

      //  CheckBox_task_01 = findViewById(R.id.checkBox_1);   //вроде он здесь не нужен, при создании задачи значение всегда будет false
        LinearLayout_Edit_subtask_01= findViewById(R.id.LinearLayout_Edit_scroll_02_xml);


        push_button_save_01(Button_01);// обработка нажатия  комент 04.06.2022


        /////
        EditText_task_01.setText(string_text_from_task);
        EditText_task_02.setText(string_text_from_data);
        System.out.println("string_text_from_data_time = "+string_text_from_data_time);
        EditText_task_03.setText(string_text_from_data_time);

        loadSubtask();
       // notification ();
       // createNotificationChannels();
      //  gon();
    }


    public void push_button_save_01(Button value) { // показывает всплывающее сообщение
        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Edit_Task_Activity.this, R.string.hi_android, Toast.LENGTH_LONG).show();

                onClick_02();
                update_subtask ();

                save_Change(calendar);

                startMainActivity();
            }
        });
    }





    // public void startMainActivity(View view) {
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //  чтобы кнопка "Сохранить" всегда была видна и не закрывалась всплывающей
    //  клавиатурой в файле AndroidManifest.xml в соответствующем разделе <activity>
    //  добавлена строка android:windowSoftInputMode="adjustResize"

    // Создадим объект Date
    Date date = new Date();


    //База данных
    String a1 = "Alex Zhe";
    // String a3 = "23.04.2022";
    String str1 = "11";
    String str2;
    String str3;
    String str3_time;
    long a3 = System.currentTimeMillis();
    long a5;
    long a6_time;
    Editable a2;
    Editable a4;
    Editable a4_time;

    boolean value_of_checkBox = false;
  //  boolean value_of_checkBox =true;


    // public void onClick(View view){

    public void onClick_02() {

        a2 = EditText_task_01.getText();
        a4 = EditText_task_02.getText();
        a4_time = EditText_task_03.getText();


        str2 = String.valueOf(a4);
        str3 = String.valueOf(a4); // перевод из формата от TextView в формат String
        str3_time = String.valueOf(a4_time); // перевод из формата от TextView в формат String

          // value_of_checkBox =CheckBox_task_01.isChecked();

/////////////////
        //перевод из строки в число
        //   String s="05.09.2013";  // нужный формат
        SimpleDateFormat format = new SimpleDateFormat();
        SimpleDateFormat format_for_a6_time = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        format_for_a6_time.applyPattern("HH:mm");
        try {
            Date docDate = format.parse(str3);
            a5 = docDate.getTime();
            Date docDate_2 = format_for_a6_time.parse(str3_time);
            a6_time = docDate_2.getTime();

            System.out.println("===============data format long from String===== " + a5);
            System.out.println("===============data format long from String_all_value_time-2===== " + str3_time);
            System.out.println("===============data format long from String_time-2===== " + a6_time);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("=======ошибка=======");
        }

/*

        // перевод строки в число
        try {
        // a3=   Long.parseLong(str1);
           // Byte b2 = Byte.valueOf(str1);
            System.out.println("==========data=======1: "+a3);
            System.out.printf("%1$s %2$td %2$tB %2$tY", "Дата:", date); // отпарсенный формат даты
            a3=   Long.parseLong(str1);
            System.out.println("==========data2======: "+a3);

        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

*/


        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);



       // db.execSQL("UPDATE users_06 SET name =  '" + a2 + "' WHERE _id='" + id_from_task + "'"); // обновление значения в базе
        db.execSQL("UPDATE users_07 SET name =  '" + a2 + "', data='" + a5 + "',time_alert='" + a6_time + "'  WHERE _id='" + id_from_task + "'"); // обновление значения в базе

        System.out.println("==check_time+dataBase==");
/*

        db.execSQL("CREATE TABLE IF NOT EXISTS users_06 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
                "data INTEGER,checkBox BOOL,done_data_fact INTEGER,time_alert INTEGER,exist_alert BOOL," +
                "exist_important BOOL)");//создание таблицы users_01 , UNIQUE - не нужен

        db.execSQL("INSERT OR IGNORE INTO users_06 (name, data, checkBox) VALUES ('" + a2 + "','" + a5 + "','" + value_of_checkBox + "');"); // добавление значения в базу



        */




        Cursor query = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы

        //  TextView textView = findViewById(R.id.textView);
        //  textView.setText("");
        int i = 0;
        while (query.moveToNext()) {
            int id_from_db= query.getInt(0);
            String name = query.getString(1);
            String data = query.getString(2);
            boolean value_check = Boolean.parseBoolean(query.getString(3));
            String done_data_fact = query.getString(4);

            //   int age = query.getInt(1);
            //    textView.append("Name: " + name + " Age: " + age + "\n");
            System.out.println("=========================id_from_db " + i + " " + id_from_db);
            System.out.println("=========================name " + i + " " + name);
            System.out.println("=========================data " + i + " " + data);
            System.out.println("=========================value_check=main " + i + " " + value_check);
            System.out.println("=========================done_data_fact " + i + " " + done_data_fact);

            i++;
        }
        query.close(); //закрываем связи
        db.close(); //закрываем связи

        /////


        ////
    }

    ArrayList<Three> my_txtView_from_Subtask_List_Three = new ArrayList<Three>(); // создание списка который
    // будет содержать в себе значения типа "Three"

    public void createObjectThreeForEditSubtask() {

        int idTask = 0;

        Three my_three_01 = new Three(new EditText(this), new TextView(this),
                new CheckBox(this), new LinearLayout(this), idTask);

        my_txtView_from_Subtask_List_Three.add(my_three_01);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void loadSubtask() {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
      //  Cursor query = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы

        System.out.println("id_from_task= "+id_from_task);
       // Cursor query_value_from_bd = db.rawQuery("SELECT * FROM users_07 WHERE _id='" + id_from_task + "'",null); // вытаскивает значения из базы (сразу нужный картеж)
       Cursor query_value_from_bd = db.rawQuery("SELECT * FROM users_07 WHERE _id=?",new String[] {String.valueOf(id_from_task)}); // вытаскивает значения из базы (сразу нужный картеж)
       // query_value_from_bd.close(); //закрываем связи

        // Cursor query_value_from_bd = db.rawQuery("SELECT name FROM users_07 WHERE _id=?",new String[] {"1"}); // вытаскивает значения из базы (сразу нужный картеж)
        //Cursor query_value_from_bd_ex = db.rawQuery("SELECT exist_subtask FROM users_07 WHERE _id=?",new String[] {"1"}); // вытаскивает значения из базы (сразу нужный картеж)

        boolean value_exist_subtask = false;

        while (query_value_from_bd.moveToNext()) {
           // int id_from_db= query_value_from_bd.getInt(0);
            //String name = query_value_from_bd.getString(1);
            value_exist_subtask = Boolean.parseBoolean(query_value_from_bd.getString(8));
        //    System.out.println("===id_from_db==="+ id_from_db);
         //   System.out.println("===name==="+ name);
            System.out.println("===value_exist_subtask==="+ value_exist_subtask);

        }

        if (value_exist_subtask==true){

            Cursor query_All_subtask = db.rawQuery("SELECT * FROM users_subtask_01;", null); // вытаскивает значения из базы
           int i =0;
           int z=0;
            while (query_All_subtask.moveToNext()) {
                int id_from_parent= query_All_subtask.getInt(3);
                boolean value_check = Boolean.parseBoolean(query_All_subtask.getString(2));
                String name = query_All_subtask.getString(1);
                int id_subtask= query_All_subtask.getInt(0);
                    if (id_from_parent==id_from_task){

                        createObjectThreeForEditSubtask();
                        my_txtView_from_Subtask_List_Three.get(z).getMy_textView().setText(name);
                        my_txtView_from_Subtask_List_Three.get(z).getMy_checkBox().setChecked(value_check);
                        my_txtView_from_Subtask_List_Three.get(z).setMy_task_id(id_subtask);


                        System.out.println("BINGO" + id_from_parent+ id_from_parent);
                        System.out.println("=========================name " + i + " " + name);


                        LinearLayout_Edit_subtask_01.addView(my_txtView_from_Subtask_List_Three.get(z).getMy_linearLayout());
// установка цвета
                        Resources resources = getResources();
                        int textColor_checked = resources.getColor(R.color.my_color_for_checked, null);
                        int textColor_black = resources.getColor(R.color.black, null);
                        if (value_check==true) {
                            my_txtView_from_Subtask_List_Three.get(z).getMy_textView().setTextColor(textColor_checked);

                        } else {
                            my_txtView_from_Subtask_List_Three.get(z).getMy_textView().setTextColor(textColor_black);

                        }

                        subtask_listenerClose(my_txtView_from_Subtask_List_Three.get(z).getMy_textView_DATA(),z, id_subtask );
                        subtask_listenerCheck(my_txtView_from_Subtask_List_Three.get(z).getMy_checkBox(),id_subtask,
                                my_txtView_from_Subtask_List_Three.get(z).getMy_textView());
                        z++;

                    }


             i++;
            }


            query_All_subtask.close();
        }





        query_value_from_bd.close(); //закрываем связи
        db.close(); //закрываем связи
    }

    public void update_subtask (){
        int sizeArr =0;
        sizeArr= my_txtView_from_Subtask_List_Three.size();
        System.out.println("sizeArr= "+sizeArr );
        Editable text_from_subtask_textView ;
        String text_from_subtask ; // перевод из формата от TextView в формат String
        int subtask_id;

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        for (int i=0;i<sizeArr;i++){
            System.out.println("==for sizeArr== "+ i +" = "+my_txtView_from_Subtask_List_Three.get(i).getMy_textView().getText());
            text_from_subtask_textView = (Editable) my_txtView_from_Subtask_List_Three.get(i).getMy_textView().getText();
            text_from_subtask = String.valueOf(text_from_subtask_textView);
            subtask_id=my_txtView_from_Subtask_List_Three.get(i).getMy_task_id();

            System.out.println("subtask_id= "+subtask_id);

            db.execSQL("UPDATE users_subtask_01 SET name =  '" + text_from_subtask + "' WHERE _id='" + subtask_id + "'"); // обновление значения в базе


        }
        db.close();

    }


    public void subtask_listenerClose(TextView TextView_Data, int counter_met,int subtask_id) {

        TextView_Data.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View TextView_Data) {
                LinearLayout_Edit_subtask_01.removeView(my_txtView_from_Subtask_List_Three.get(counter_met).getMy_linearLayout());
                my_txtView_from_Subtask_List_Three.remove(counter_met);

                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
              //  db.execSQL("UPDATE users_subtask_01 SET checkBox =  '" + value_check_subtask + "' WHERE _id='" + subtask_id + "'"); // обновление значения в базе
                db.execSQL("DELETE FROM users_subtask_01  WHERE _id='" + subtask_id + "'"); // удаление
                db.close();

            }
        });
    }


    public void subtask_listenerCheck(CheckBox checkBox, int subtask_id, TextView textView) {

        checkBox.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
               // LinearLayout_Edit_subtask_01.removeView(my_txtView_from_Subtask_List_Three.get(counter_met).getMy_linearLayout());
             //   my_txtView_from_Subtask_List_Three.remove(counter_met);
                Resources resources = getResources();
                int textColor_checked = resources.getColor(R.color.my_color_for_checked, null);
                int textColor_black = resources.getColor(R.color.black, null);
                boolean value_checkBox_for_DB;
                if (checkBox.isChecked()) {
                    textView.setTextColor(textColor_checked);
                    value_checkBox_for_DB = true;
                } else {
                    textView.setTextColor(textColor_black);
                    value_checkBox_for_DB = false;

                }


                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
                db.execSQL("UPDATE users_subtask_01 SET checkBox =  '" + value_checkBox_for_DB + "' WHERE _id='" + subtask_id + "'"); // обновление значения в базе
                System.out.println("=====checkBox===");
                db.close();
            }
        });
    }

    public void add_Edit_Subtask_01(View view){
        /*
        System.out.println("===addSubtask2==1");
        createObjectThreeForSubtask();
        System.out.println("===addSubtask2==2");
        // ScrollView_task_01.addView(my_txtView_from_Subtask_List_Three.get(0).getMy_linearLayout());
        LinearLayout_task_01.addView(my_txtView_from_Subtask_List_Three.get(1).getMy_linearLayout());
        System.out.println("===addSubtask2==3");
*/
        System.out.println("checks-0");

       int counter=my_txtView_from_Subtask_List_Three.size();
       int z=counter;
       boolean exist_subtask_true = true;
        System.out.println("checks-1");
        createObjectThreeForEditSubtask();
        System.out.println("checks-2");

        LinearLayout_Edit_subtask_01.addView(my_txtView_from_Subtask_List_Three.get(counter).getMy_linearLayout());
        System.out.println("checks-3");

/*

        if (counter==5) {
          //  LinearLayout_task_01.removeAllViewsInLayout();
            LinearLayout_task_01.removeView(my_txtView_from_Subtask_List_Three.get(1).getMy_linearLayout());
        }
*/
        Editable text_from_subtask_textView ;
        String text_from_subtask ; // перевод из формата от TextView в формат String
        text_from_subtask_textView = (Editable) my_txtView_from_Subtask_List_Three.get(counter).getMy_textView().getText();
        text_from_subtask = String.valueOf(text_from_subtask_textView);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);


        db.execSQL("INSERT OR IGNORE INTO users_subtask_01 (name, checkBox, parent_task_id) VALUES ('" + text_from_subtask + "','" + value_of_checkBox + "','" + id_from_task + "');"); // добавление значения в базу

        db.execSQL("UPDATE users_07 SET exist_subtask =  '" + exist_subtask_true + "' WHERE _id='" + id_from_task + "'"); // обновление значения в базе


        System.out.println("==checks= id_from_task== "+id_from_task);

        Cursor query = db.rawQuery("SELECT * FROM users_subtask_01;", null); // вытаскивает значения из базы
        int last_subtask_id=0;
        while (query.moveToNext()) {
            last_subtask_id= query.getInt(0);
            System.out.println("last_parent_id = "+last_subtask_id);
        }
        query.close(); //закрываем связи

        my_txtView_from_Subtask_List_Three.get(counter).setMy_task_id(last_subtask_id);
        int id_subtask=last_subtask_id;

        System.out.println("checks-4");
//14/06/2022
        subtask_listenerClose(my_txtView_from_Subtask_List_Three.get(z).getMy_textView_DATA(),z, id_subtask );
        subtask_listenerCheck(my_txtView_from_Subtask_List_Three.get(z).getMy_checkBox(),id_subtask,
                my_txtView_from_Subtask_List_Three.get(z).getMy_textView());


       // subtask_listener(my_txtView_from_Subtask_List_Three.get(counter).getMy_textView_DATA(),counter);
        System.out.println("checks-5");

      //  counter++;
        System.out.println("checks-6 = plus counter=" + counter);
        db.close(); //закрываем связи
    }

    public void save_added_Edit_Subtask_01(View view){



    }


    /////////////////////////////////////////////////////////
   //уведомления

    // Идентификатор уведомления
    private static final int NOTIFY_ID = 101;

    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";


    public void notification (){
/*

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(Edit_Task_Activity.this, CHANNEL_ID)
                        //   .setSmallIcon(R.drawable.ic_pets_black_24dp)
                        .setSmallIcon(R.drawable.ic_image_01)
                        .setContentTitle("Напоминание")
                        .setContentText("Пора покормить кота")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(Edit_Task_Activity.this);
        notificationManager.notify(NOTIFY_ID, builder.build());
*/

        System.out.println("notification");

        ///////////
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text");

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

        System.out.println("notification2");

      /////////////
      /*  NotificationCompat.Builder builder =
                //   builder =
                new NotificationCompat.Builder(Edit_Task_Activity.this, CHANNEL_ID)
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
        notificationManager.notify(NOTIFY_ID, builder.build());*/

        ////////





    }

    public static final  String CHANNEL_1_ID = "channel1";
    public static final  String CHANNEL_2_ID = "channel2";

    private void createNotificationChannels()  {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel1.setDescription("This is channel 2");


            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
        System.out.println("notification3");
    }


 /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(Edit_Task_Activity.this, CHANNEL_ID)
                             //   .setSmallIcon(R.drawable.ic_pets_black_24dp)
                                .setSmallIcon(R.drawable.ic_image_01)
                                .setContentTitle("Напоминание")
                                .setContentText("Пора покормить кота")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(Edit_Task_Activity.this);
                notificationManager.notify(NOTIFY_ID, builder.build());
            }
        });
    }
}

    */

   // Edit_Task_Activity Obj_for_alarm = new Edit_Task_Activity();

    public void startChooseData_Edit_TA(View view)  {
        System.out.println("startChooseData()-1");
        callDatePicker();

        System.out.println("startChooseData()-2");

   //     Obj_for_alarm.callDatePicker();

    }
    Calendar calendar_for_picker = Calendar.getInstance();
    long long_check_calendar=0;




    public void callDatePicker() {




        //  // Calendar calendar_for_picker = Calendar.getInstance();
        //  calendar_for_picker.setTimeInMillis((Long) datePicker.getSelection());
        long_check_calendar=calendar_for_picker.getTimeInMillis();

        //datePicker.show(getSupportFragmentManager(), "tag_picker_Data");

        System.out.println("==calendar_for_picker1==="+calendar_for_picker.getTimeInMillis());
        System.out.println("==long_check_calendar1==="+long_check_calendar);



        // установка обработчика выбора даты
        DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar_for_picker.set(Calendar.YEAR, year);
                calendar_for_picker.set(Calendar.MONTH, monthOfYear);
                calendar_for_picker.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //setInitialDateTime();
                long_check_calendar=calendar_for_picker.getTimeInMillis();
                System.out.println("==calendar_for_picker_date 100500==="+calendar_for_picker);
                System.out.println("==calendar_for_picker 100500==="+calendar_for_picker.getTimeInMillis());
                System.out.println("==long_check_calendar 100500==="+long_check_calendar);

                callAlarmManager2(); //
            }
        };


        new DatePickerDialog(Edit_Task_Activity.this, d,
                calendar_for_picker.get(Calendar.YEAR),
                calendar_for_picker.get(Calendar.MONTH),
                calendar_for_picker.get(Calendar.DAY_OF_MONTH))
                .show();

    }

  //  Edit_Task_Activity eta_for_alarm = new Edit_Task_Activity();
  //  ArrayList<Edit_Task_Activity> eta_for_alarm_List = new ArrayList<Edit_Task_Activity>(); // создание списка который



    private void callAlarmManager2() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault());
        SimpleDateFormat sdf_for_EditText = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        SimpleDateFormat sdf_for_EditText_Time = new SimpleDateFormat("HH:mm", Locale.getDefault());


        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время для будильника")
                .build();


        materialTimePicker.addOnPositiveButtonClickListener(view -> {
          //  Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
            calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
            /////// проверка при добавлении даты
            calendar.set(Calendar.YEAR, calendar_for_picker.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, calendar_for_picker.get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, calendar_for_picker.get(Calendar.DAY_OF_MONTH));



            Toast.makeText(this, "Будильник установлен на " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();

///////// work
           /*
            Intent my_intent = new Intent(getApplicationContext(), NotificationReceiver.class);
            PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
*/
//////////
           // eta_for_alarm_List.add(my_two_01);

          //  eta_for_alarm.instance_callAlarmManager2(calendar,eta_for_alarm); //

             st_time_notif = sdf_for_EditText_Time.format(calendar.getTime());
             st_data_notif =sdf_for_EditText.format(calendar.getTime());

              instance_callAlarmManager2 (calendar, Edit_Task_Activity.this);

             // Alarm alarm = new Alarm();


             // alarm.instance_callAlarmManager2(calendar,Edit_Task_Activity.this);
          //  alarm_List.get(0).instance_callAlarmManager2(calendar,Edit_Task_Activity.this);


            //alarm_List.add(alarm);
            //alarm_List.get(0).print();
            //alarm_List.get(0).instance_callAlarmManager2(calendar, Edit_Task_Activity.this);

           // my_txtView_from_Subtask_List_Three.get(1).instance_callAlarmManager2(calendar,Edit_Task_Activity.this);
           // my_txtView_from_Subtask_List_Three.get(1).instance_callAlarmManager2(calendar,Edit_Task_Activity.this);
            System.out.println("==check instance_callAlarmManager2 -1==");



            /////установка значения в EditText
            EditText_task_02.setText(sdf_for_EditText.format(calendar.getTime()));
            EditText_task_03.setText(sdf_for_EditText_Time.format(calendar.getTime()));
        });


        materialTimePicker.show(getSupportFragmentManager(), "tag_picker");
    }

    int count_call= (int) id_from_task;

    ArrayList<String> Notification_List = new ArrayList<String>(); // создание списка

    private static final String PREFS_FILE = "Account_Notification";
    SharedPreferences settings;// = getSharedPreferences("PreferencesName", MODE_PRIVATE);
   // SharedPreferences settings= getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
   MainActivity ma = new MainActivity();


    public void instance_callAlarmManager2(Calendar calendar, Edit_Task_Activity eta_for_alarm2) {
/*

       Intent my_intent = new Intent(getApplicationContext(), NotificationReceiver.class);
       // Intent my_intent = new Intent(eta_for_alarm2, NotificationReceiver.class);
       // PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent =     PendingIntent.getBroadcast(eta_for_alarm2,count_call, my_intent,  PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(eta_for_alarm2.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
*/
//////////////////////////////

      // ArrayList<String> Notification_List = new ArrayList<String>(); // создание списка

        //////
        // SimpleDateFormat formatter = new SimpleDateFormat("E'.' dd.MM.yy HH:mm "); // класс для форматирования
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm"); // класс для форматирования
        //  Date date = new Date(System.currentTimeMillis());
        Date current_date_Notification = new Date(); // при создании объекта автоматом задается текущая дата
        String cur_Time_for_Notification=formatter.format(current_date_Notification);
        long long_for_cur_Time_for_Notification=0;
        System.out.println("===========formatter.format(current_date)==" + formatter.format(current_date_Notification));
        System.out.println("===========formatter.format(current_date)==" + cur_Time_for_Notification);

        SimpleDateFormat format_for_a6_time_Notification = new SimpleDateFormat();
        format_for_a6_time_Notification.applyPattern("HH:mm");

        try {
            Date docDate_2 = format_for_a6_time_Notification.parse(cur_Time_for_Notification);
            long_for_cur_Time_for_Notification = docDate_2.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("===========long_for_cur_Time_for_Notification==" + long_for_cur_Time_for_Notification);

      //  ArrayList<Long> Notification_List = new ArrayList<Long>(); // создание списка


        /////



        int q = 0;
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

                Notification_List.add(name);

                System.out.println("===data_long_time=== "+data_long_time);
                if (id_for_object==count_call){

             //   if (name=="5"){
                    string_text_for_notification = name;
                    System.out.println("===data_long_time=== "+data_long_time);
                }


            }
        }


        query.close(); //закрываем связи
        db.close(); //закрываем связи




//////////////////////////////////////////////////////////////////////////////////

/*

       // string_text_for_notification="la-la-la";
      //  string_text_for_notification= String.valueOf(count_call);


        Intent my_intent = new Intent(Edit_Task_Activity.this, NotificationReceiver.class);  //должно быть так что бы работало
        // Intent my_intent = new Intent(eta_for_alarm2, NotificationReceiver.class);
        // PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent =     PendingIntent.getBroadcast(Edit_Task_Activity.this,count_call, my_intent,  PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Edit_Task_Activity.this.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

        //////////////////
        // сохраняем его в настройках
        String key = String.valueOf(count_call);

      //  SharedPreferences settings = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        settings = getSharedPreferences("PreferencesName2",MODE_PRIVATE );
       // SharedPreferences.Editor prefEditor = ma.settings.edit();

       SharedPreferences.Editor prefEditor = settings.edit();
      //  prefEditor.putString(key, string_text_for_notification);  //ключ, значение
      //  prefEditor.putString("6", "string_text_for_notification");  //ключ, значение

     //   prefEditor.clear();

        ///delete

       // String id="44";
        String id=String.valueOf(count_call);
       // String time="00:44";
      //  String time=cur_Time_for_Notification;
        String time= st_time_notif;
       // String time=String.valueOf(EditText_task_03.getText());
       // String data="04.07.2022";
       // String data=st_data_notif;
       // String data=String.valueOf(EditText_task_02.getText());
        String data=st_data_notif;
      //  String task="task N100500";
       // String task=string_text_from_task;
        String task= String.valueOf(EditText_task_01.getText());

      */
/*
        String key_time_id=time+"="+id;
        String key_time_data=time+"="+data;
        String key_time_task=time+"="+task;
        *//*

        String main_key = id+"="+time+"="+data+"="+task;

*/
/*
        prefEditor.putString("22:15", "string_text_for_notification");  //ключ, значение
        prefEditor.putString(key_time_id, id);  //ключ, значение
        prefEditor.putString(key_time_data, data);  //ключ, значение
        prefEditor.putString(key_time_task, task);  //ключ, значение
        *//*

        prefEditor.putString(main_key, "empty");  //ключ, значение все записано в ключ

        System.out.println("==main_key== /// "+ main_key);

       // String[] arrSt = new String[] { "data", "time", "text of task" };
        //LinkedHashSet<String> myHashSet = new LinkedHashSet<String>();
      */
/*
        Set<String> myHashSet = new LinkedHashSet<String>();
        myHashSet.add("time");
        myHashSet.add("tadata");
        myHashSet.add("tadata");
        myHashSet.add("text of task");

        prefEditor.putStringSet("7", myHashSet);
*//*

        //putStringSet(String key, Set<String> values)
        prefEditor.apply();
*/

//////////////////////////////////////////////////////////////////////////////////


  //      System.out.println("======next from SharedPreferences key===== "+key);

    //    String PREF_NAME = "5";
    //    String name = settings.getString(PREF_NAME,"не определено");
     //   System.out.println("======next from SharedPreferences====="+name);

       /////////////////

        System.out.println("==count_call=="+ count_call);
       // count_call++;

    }

    public void save_Change(Calendar calendar) {

            System.out.println("==save_Change==1==");

            String St_id = String.valueOf(count_call);
            settings = getSharedPreferences("PreferencesName2", MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = settings.edit();


            ////////////  удаляет из настроек ранее созданную запись, если такая была создана ранее
            Map<String, ?> my_Map = settings.getAll();

            System.out.println("==first all== " + my_Map);

            //перебор мапы
            //попробовать собрать трехмерный массив и с ним работать
            for (Map.Entry<String, ?> item : my_Map.entrySet()) {

                String tempTime = item.getKey();
                String delimeter = "=";
                //String[] subStrTime = tempTime.split(delimeter, 2); //массив  разбивает на 2 части
                String[] main_subStrTime = tempTime.split(delimeter, 5); //массив  разбивает на 4 части
                //  System.out.println("==subStrTime[0]== "+subStrTime[0]);
                System.out.println("==subStrTime[0]=id= " + main_subStrTime[0]);


                //   if (cur_Time_for_Notification==subStrTime[0]){
                // if (cur_Time_for_Notification.equals(subStrTime[0])){  // сравниваем с первым элементом массива
                if (St_id.equals(main_subStrTime[0])) {  // сравниваем с нулевым элементом массива (id)

                    //  System.out.println("BINGO! "+"key =  " + item.getKey() + " value = " + item.getValue());
                    System.out.println("delete old " + "key =  " + item.getKey() + "/ value = " + main_subStrTime[3]);
                    prefEditor.remove(tempTime);
                    prefEditor.apply();

                }

            }


            //////////////// создает новыю запись в настройках
        if(!(EditText_task_03.getText().toString().equals(""))) {


            Intent my_intent = new Intent(Edit_Task_Activity.this, NotificationReceiver.class);  //должно быть так что бы работало
            // Intent my_intent = new Intent(eta_for_alarm2, NotificationReceiver.class);
            // PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Edit_Task_Activity.this, count_call, my_intent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Edit_Task_Activity.this.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            //////////////////
            // сохраняем его в настройках
            String key = String.valueOf(count_call);

            //  SharedPreferences settings = getSharedPreferences("PreferencesName", MODE_PRIVATE);

            //   settings = getSharedPreferences("PreferencesName2",MODE_PRIVATE );// ====

            // SharedPreferences.Editor prefEditor = ma.settings.edit();

            //SharedPreferences.Editor prefEditor = settings.edit(); //===

            //  prefEditor.putString(key, string_text_for_notification);  //ключ, значение
            //  prefEditor.putString("6", "string_text_for_notification");  //ключ, значение

            //   prefEditor.clear();  // удалит все ожидающие уведомления

            ///delete

            // String id="44";
            String id = String.valueOf(count_call);
            // String time="00:44";
            //  String time=cur_Time_for_Notification;
            String time = st_time_notif;
            // String time=String.valueOf(EditText_task_03.getText());
            // String data="04.07.2022";
            // String data=st_data_notif;
            // String data=String.valueOf(EditText_task_02.getText());
            String data = st_data_notif;
            //  String task="task N100500";
            // String task=string_text_from_task;
            String task = String.valueOf(EditText_task_01.getText());

            String checkBox = checkBox_notif;

      /*
        String key_time_id=time+"="+id;
        String key_time_data=time+"="+data;
        String key_time_task=time+"="+task;
        */
        //    String main_key = id + "=" + time + "=" + data + "=" + task;
            String main_key = id + "=" + time + "=" + data + "=" + task + "=" + checkBox;

/*
        prefEditor.putString("22:15", "string_text_for_notification");  //ключ, значение
        prefEditor.putString(key_time_id, id);  //ключ, значение
        prefEditor.putString(key_time_data, data);  //ключ, значение
        prefEditor.putString(key_time_task, task);  //ключ, значение
        */
            prefEditor.putString(main_key, "empty");  //ключ, значение все записано в ключ

            System.out.println("==main_key== /// " + main_key);

            // String[] arrSt = new String[] { "data", "time", "text of task" };
            //LinkedHashSet<String> myHashSet = new LinkedHashSet<String>();
      /*
        Set<String> myHashSet = new LinkedHashSet<String>();
        myHashSet.add("time");
        myHashSet.add("tadata");
        myHashSet.add("tadata");
        myHashSet.add("text of task");

        prefEditor.putStringSet("7", myHashSet);
*/
            //putStringSet(String key, Set<String> values)
            prefEditor.apply();

        }
    }




    String st_for_notif;
    public String getArrayValue(int num_of) {

        st_for_notif=Notification_List.get(num_of);
        System.out.println("==st_for_not=="+ st_for_notif);
        return st_for_notif;
    }

    public String gg(){
        settings = getSharedPreferences("PreferencesName2",MODE_MULTI_PROCESS );
        String PREF_NAME = "5";
        String name = settings.getString(PREF_NAME,"не определено");
        st_for_notif=name;
        return st_for_notif;
    }
/*

    public void gon(){   // убрать этот гон!!!

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        Cursor query = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы

        //  TextView textView = findViewById(R.id.textView);
        //  textView.setText("");
        int i = 0;
        while (query.moveToNext()) {
            int id_from_db= query.getInt(0);
            String name = query.getString(1);
            Long data = query.getLong(2);
            boolean value_check = Boolean.parseBoolean(query.getString(3));
            String done_data_fact = query.getString(4);
            long alarm_time = query.getLong(5);

            long puf =data+alarm_time;

            //   int age = query.getInt(1);
            //    textView.append("Name: " + name + " Age: " + age + "\n");
            System.out.println("=========================id_from_db " + i + " " + id_from_db);
            System.out.println("=========================name " + i + " " + name);
            System.out.println("=========================data " + i + " " + data);
            System.out.println("=========================value_check=main " + i + " " + value_check);
            System.out.println("=========================done_data_fact " + i + " " + done_data_fact);
            System.out.println("=========================alarm_time " + i + " " + alarm_time);


            int last_id_from_task_MA=id_from_db;
            int count_call_TA= (int)last_id_from_task_MA;

            Intent my_intent = new Intent(Edit_Task_Activity.this, NotificationReceiver.class);  //должно быть так что бы работало
            // Intent my_intent = new Intent(eta_for_alarm2, NotificationReceiver.class);
            // PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            //PendingIntent pendingIntent =     PendingIntent.getBroadcast(Task_Activity.this,count_call_TA, my_intent,  PendingIntent.FLAG_ONE_SHOT);
            PendingIntent pendingIntent =     PendingIntent.getBroadcast(Edit_Task_Activity.this,count_call_TA, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Edit_Task_Activity.this.ALARM_SERVICE);
            //  alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            alarmManager.set(AlarmManager.RTC_WAKEUP,puf,pendingIntent);



            i++;

        }


        query.close(); //закрываем связи
        db.close(); //закрываем связи

    }
*/

    public void resetTime(View view){

        EditText_task_03.setText("");

    }
    public void resetData(View view){

        EditText_task_02.setText("");
        EditText_task_03.setText("");
    }
}