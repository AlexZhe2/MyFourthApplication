package com.example.myfourthapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageButton ImageButton_01;
    private ImageButton ImageButton_02;
    private ImageButton ImageButton_03;
    private ImageButton ImageButton_04;

    private LinearLayout my_LinerLayout_01;
    private LinearLayout my_LinerLayout_Today_01;
    private LinearLayout my_LinerLayout_Tomorrow_01;
    private LinearLayout my_LinerLayout_Week_01;
    private LinearLayout my_LinerLayout_Month_01;
    private LinearLayout my_LinerLayout_Late_01;

    private TextView TextView_for_data_01;

    SharedPreferences settings;// = getSharedPreferences("PreferencesName", MODE_PRIVATE);
   // SharedPreferences settings = getSharedPreferences("PreferencesName", MODE_PRIVATE);


    int j = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);//присвоение ранее созданной переменной
        my_LinerLayout_Today_01 = findViewById(R.id.my_LinerLayout_Today_xml_01);//присвоение ранее созданной переменной
        my_LinerLayout_Tomorrow_01 = findViewById(R.id.my_LinerLayout_Tomorrow_xml_01);//присвоение ранее созданной переменной
        my_LinerLayout_Week_01 = findViewById(R.id.my_LinerLayout_Week_xml_01);//присвоение ранее созданной переменной
        my_LinerLayout_Month_01 = findViewById(R.id.my_LinerLayout_Month_xml_01);//присвоение ранее созданной переменной
        my_LinerLayout_Late_01 = findViewById(R.id.my_LinerLayout_Late_xml_01);//присвоение ранее созданной переменной
        // типа LinearLayout конкретного значения LinearLayout из XML файла
        ImageButton_01 = findViewById(R.id.ImageButton_xml_01);
        ImageButton_02 = findViewById(R.id.ImageButton_xml_02);
        ImageButton_03 = findViewById(R.id.ImageButton_xml_03);
        ImageButton_04 = findViewById(R.id.ImageButton_xml_04);

        TextView_for_data_01 = findViewById(R.id.TextView_for_data_xml_01);

        showToastFunction_01(ImageButton_01);// обработка нажатия
        showToastFunction_01(ImageButton_02);
        showToastFunction_01(ImageButton_03);
        // showToastFunction_01(ImageButton_04);

        // add_task_04(ImageButton_04); // добавление новой задачи
        //  fill_Layout_for_tasks_02();
        create_BD_01();//  создаем базу данных


        fill_Layout_for_tasks_03();
        set_Data_in_main_activity();
        create_Channel();
        //gon();

      //  trueNotification(); /// тест потом удалить
    }

    public void set_Data_in_main_activity() {

        Date date_for_main_activity = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd"); // описание http://proglang.su/java/date-and-time
        String short_data_for_main_activity = "";
        short_data_for_main_activity = formatForDateNow.format(date_for_main_activity);

        TextView_for_data_01.setText(short_data_for_main_activity);
    }


    public void create_BD_01() { //  создаем базу данных
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        //  db.execSQL("CREATE TABLE IF NOT EXISTS users_06 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,data INTEGER,checkBox BOOL, UNIQUE(name))");//создание таблицы users_01
        //  db.execSQL("CREATE TABLE IF NOT EXISTS users_06 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,data INTEGER,checkBox BOOL)");//создание таблицы users_01 , UNIQUE - не нужен
        //  db.execSQL("DROP TABLE IF EXISTS  users_07"); //удаление таблицы
       //  db.execSQL("DROP TABLE IF EXISTS  users_subtask_01"); //удаление таблицы

        db.execSQL("CREATE TABLE IF NOT EXISTS users_07 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
                "data INTEGER,checkBox BOOL,done_data_fact INTEGER,time_alert INTEGER,exist_alert BOOL," +
                "exist_important BOOL,exist_subtask BOOL)");//создание таблицы users_01 , UNIQUE - не нужен


        // поле "name" в ней текстовое и уникальное (UNIQUE(name)) но это не точно:-)

        db.close(); //закрываем связи
    }
    // Идентификатор уведомления
    private static final int NOTIFY_ID = 101;

    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";

    public void showToastFunction_01(ImageButton value) { // показывает всплывающее сообщение
        value.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
            //    Toast.makeText(MainActivity.this, R.string.hi_android, Toast.LENGTH_LONG).show();
                ///уведомления
    // перенес в  trueNotification();
/*
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                //   .setSmallIcon(R.drawable.ic_pets_black_24dp)
                                .setSmallIcon(R.drawable.ic_image_01)
                             //   .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Напоминание")
                                .setContentText("Пора покормить кота")
                              //  .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                .setPriority(NotificationCompat.PRIORITY_HIGH);

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(NOTIFY_ID, builder.build());


                System.out.println("notification4");
                */
/////
                notif();//delete
                addNotification();//delete

             //   trueNotification();

                callDatePicker(); // show dataPicker

             //   callAlarmManager();  // alarmManager


            }



        });
    }

    public void trueTestResiver(){
        System.out.println("==Test Resiver==");
    }

    NotificationCompat.Builder builder;
    NotificationManagerCompat notificationManager;

    public void trueNotification(){

        NotificationCompat.Builder builder =
      //   builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
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


        test(); // delete
    }






/*

    public void trueNotification2(){
        notificationManager.notify(NOTIFY_ID, builder.build());
        System.out.println("trueNotification2");
    }
*/




    public void create_Channel(){  //обязательно нужно сначало настроить канал для уведомлений!!!
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Stella channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }



    }


public void notif(){ //delete
    create_Channel ();


    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
  //  mBuilder.setSmallIcon(R.drawable.notification_icon);
    mBuilder.setContentTitle("Notification Alert, Click Me!");
    mBuilder.setContentText("Hi, This is Android Notification Detail!");

}
    @RequiresApi(api = Build.VERSION_CODES.M)//delete
    private void addNotification() {//delete
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                      //  .setSmallIcon(R.drawable.abc)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
        System.out.println("notification-5");
        System.out.println(android.os.Build.VERSION.SDK_INT);

    }

    ///////////// вызов Alarm Manager


    Calendar calendar_for_picker = Calendar.getInstance();
    long long_check_calendar=0;
    public void callDatePicker() {

        /////////////установка даты
      //  MaterialDatePicker.Builder data_builder = MaterialDatePicker.Builder.datePicker();
     //   MaterialDatePicker.Builder data_builder =  MaterialDatePicker.BuilderdatePicker()
      //          .setTitleText("Select date")
      //          .build();
/*

        MaterialDatePicker datePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                       // .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

*/





     //  // Calendar calendar_for_picker = Calendar.getInstance();
      //  calendar_for_picker.setTimeInMillis((Long) datePicker.getSelection());
        long_check_calendar=calendar_for_picker.getTimeInMillis();

        //datePicker.show(getSupportFragmentManager(), "tag_picker_Data");

        System.out.println("==calendar_for_picker1==="+calendar_for_picker.getTimeInMillis());
        System.out.println("==long_check_calendar1==="+long_check_calendar);

      //  test();

        ///////////////////////////////

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



        // отображаем диалоговое окно для выбора даты
     //   public void setDate(View v) {
     /*   public void setDate() {
            new DatePickerDialog(MainActivity.this, d,
                    calendar_for_picker.get(Calendar.YEAR),
                    calendar_for_picker.get(Calendar.MONTH),
                    calendar_for_picker.get(Calendar.DAY_OF_MONTH))
                    .show();
        }*/

        new DatePickerDialog(MainActivity.this, d,
                calendar_for_picker.get(Calendar.YEAR),
                calendar_for_picker.get(Calendar.MONTH),
                calendar_for_picker.get(Calendar.DAY_OF_MONTH))
                .show();



/////////////////////////







        test();

    }
    private void test() {

        System.out.println("==calendar_for_picker2==="+calendar_for_picker.getTimeInMillis());
        System.out.println("==long_check_calendar2==="+long_check_calendar);

    }


    private void callAlarmManager() {
        test();
        System.out.println("callAlarmManager-1");
        /*

        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время для будильника")
                .build();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
*/

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault());

        ImageButton_02 = findViewById(R.id.ImageButton_xml_02);
        ImageButton_02.setOnClickListener(v -> {
            MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(0)
                    .setTitleText("Выберите время для будильника")
                    .build();

            materialTimePicker.addOnPositiveButtonClickListener(view -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
                /////// проверка при добавлении даты
                calendar.set(Calendar.YEAR, calendar_for_picker.get(Calendar.YEAR));
                calendar.set(Calendar.MONTH, calendar_for_picker.get(Calendar.MONTH));
                calendar.set(Calendar.DAY_OF_MONTH, calendar_for_picker.get(Calendar.DAY_OF_MONTH));




/////
 //               AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

 //               AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), getAlarmInfoPendingIntent());

 //               alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent());

///

                Toast.makeText(this, "Будильник установлен на " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();




/*

                ///////////////////////////////
                Intent intent = new Intent(this, NotificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


                ////////////////////////////////////
*/
                Intent my_intent = new Intent(getApplicationContext(), NotificationReceiver.class);
                PendingIntent   pendingIntent =    PendingIntent.getBroadcast(MainActivity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                 alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);





            });



            materialTimePicker.show(getSupportFragmentManager(), "tag_picker");
        });

        System.out.println("callAlarmManager-2");


    }

    private void callAlarmManager2() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault());

        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время для будильника")
                .build();


        materialTimePicker.addOnPositiveButtonClickListener(view -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
                    calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
                    /////// проверка при добавлении даты
                    calendar.set(Calendar.YEAR, calendar_for_picker.get(Calendar.YEAR));
                    calendar.set(Calendar.MONTH, calendar_for_picker.get(Calendar.MONTH));
                    calendar.set(Calendar.DAY_OF_MONTH, calendar_for_picker.get(Calendar.DAY_OF_MONTH));



            Toast.makeText(this, "Будильник установлен на " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();

            Intent my_intent = new Intent(getApplicationContext(), NotificationReceiver.class);
            PendingIntent   pendingIntent =    PendingIntent.getBroadcast(MainActivity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        });


        materialTimePicker.show(getSupportFragmentManager(), "tag_picker");
    }






    /*public class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
           *//* if (reminder != null) {
                // Запускаем уведомление
                MyNotification.notify(context, message, number);
            }*//*
            trueNotification();

            System.out.println("Receiver");
        }


    }
    */




    private PendingIntent getAlarmInfoPendingIntent() {
        System.out.println("callAlarmManager-3");

       // Intent alarmInfoIntent = new Intent(this, MainActivity.class);
          Intent alarmInfoIntent = new Intent(this, NotificationReceiver.class);
        alarmInfoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        trueNotification();
        System.out.println("callAlarmManager-4");
        return PendingIntent.getActivity(this, 0, alarmInfoIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private PendingIntent getAlarmActionPendingIntent() {

        System.out.println("callAlarmManager-5");

       // Intent intent = new Intent(this, MainActivity.class);
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        trueNotification();
        System.out.println("callAlarmManager-6");
        return PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    }





    // открытие другого окна (activity)
    // метод вызывается с помощью прописанного в xml файле "activity_main.xml" для
    // кнопки Button ("@+id/button3") и кнопки ImageButton ("@+id/ImageButton_xml_04")
    // атрибута android:onClick="startTaskActivity"
    public void startTaskActivity(View view) {
        Intent intent = new Intent(this, Task_Activity.class);
        startActivity(intent);
        System.out.println("==========start=========");

/*
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы

        while (query.moveToNext()) {
            String name = query.getString(0);
            String data = query.getString(1);

            //   int age = query.getInt(1);
            //    textView.append("Name: " + name + " Age: " + age + "\n");
            System.out.println("From main========================= " + i + " " + name);
            System.out.println("From main========================= " + i + " " + data);
            i++;
        }
        query.close(); //закрываем связи
        db.close(); //закрываем связи
        */
        ////

    }
    public void startEditTaskActivity(View view) {
        Intent intent_for_edit_task = new Intent(this, Edit_Task_Activity.class);
        startActivity(intent_for_edit_task);
        System.out.println("==========start=========");
    }




    ArrayList<Two> my_txtView_from_List_Two = new ArrayList<Two>(); // создание списка который
    // будет содержать в себе значения типа "Two"

    // метод который инициирует создание объекта (класа) Two с необходимыми для работы атрибутами
    // после создания ложит экземпляры созданных объектов в список. Работаем со списком через переменную "int i=0"
    public void createObjectTwo() {

        int idTask = 0;

        Two my_two_01 = new Two(new TextView(this), new TextView(this),
                new CheckBox(this), new LinearLayout(this), idTask);

        my_txtView_from_List_Two.add(my_two_01);
    }

    ArrayList<Four> my_txtView_from_List_Four = new ArrayList<Four>(); // создание списка который
    // будет содержать в себе значения типа "Four"
    public void createObjectFour() {

        boolean have_notification=false;
        boolean have_subtask=false;

        Four my_Four_01 = new Four(new TextView(this), new TextView (this),
                have_notification, have_subtask, new ImageView(this), new ImageView(this), new LinearLayout(this));

        my_txtView_from_List_Four.add(my_Four_01);
        System.out.println("object Four");

    }




    int i = 0;

    public void add_task_04(ImageButton value) {  // добавление новой задачи
        value.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                createObjectTwo();

                if (i == 3) {
                    my_txtView_from_List_Two.get(i).getMy_textView().setText("Hello-Hello Android!!!");
                }

                if (i == 5) {
                    my_txtView_from_List_Two.get(i).getMy_textView().
                            setText("It is the test of long line");
                }

                my_LinerLayout_01.addView(my_txtView_from_List_Two.get(i).getMy_linearLayout()); // добавляем на
                // нужное нам поле созданные объекты Two из списка - обращаясь к ним по индексу i
                my_txtView_from_List_Two.get(i).getMy_linearLayout().requestFocusFromTouch(); // устанавливает фокус
                //на последнюю созданную задачу

                i++;

                ////
                /*
                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

                Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы

                while (query.moveToNext()) {
                    // вписать  createObjectTwo() и в коде задать значения для полей с задачей и вызывать
                    // не по кнопке, а по умолчанию (при запуске экрана = Activity) из метода
                    // protected void onCreate(Bundle savedInstanceState)
                    //
                    // сделать my_LinerLayout_01 для сегодня, завтра, после завтра, на неделе, потом
                    // проходить циклом по дате для задачи и добавляем на соответствующий LinerLayout через
                    //  my_LinerLayout_01.addView(my_txtView_from_List_Two.get(i).getMy_linearLayout());
                    String name = query.getString(0);
                    String data = query.getString(1);

                    //   int age = query.getInt(1);
                    //    textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("========================= " + i + " " + name);
                    System.out.println("========================= " + i + " " + data);
                    i++;
                }
*/
                ////
            }

        });
    }

    //  int j = 0;
    int k = 0;

    public void fill_Layout_for_tasks(View view) {
        ////
        //  int j = 0;
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        //  Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы
        Cursor query = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы


        k = query.getCount();

        if (j == 0) {

            System.out.println("============= if(j==0) " + j);
            ///
            while (query.moveToNext()) {
                //  while (query.moveToPosition(4)) {
                // вписать  createObjectTwo() и в коде задать значения для полей с задачей и вызывать
                // не по кнопке, а по умолчанию (при запуске экрана = Activity) из метода
                // protected void onCreate(Bundle savedInstanceState)
                //
                // сделать my_LinerLayout_01 для сегодня, завтра, после завтра, на неделе, потом
                // проходить циклом по дате для задачи и добавляем на соответствующий LinerLayout через
                //  my_LinerLayout_01.addView(my_txtView_from_List_Two.get(i).getMy_linearLayout());


                // String name = query.getString(0);
                String name = query.getString(1);
                String data = query.getString(2);

                createObjectTwo();
                my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText(data);
                my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());


                //    int age = query.getInt(1);
                //     textView.append("Name: " + name + " Age: " + age + "\n");
                System.out.println("from first========================= " + j + " " + name);
                System.out.println("from first========================= " + j + " " + data);
                j++;


            }
            ///

        } else {
            while (query.move(j + 1)) {


                System.out.println("============= while (query.move(j)) " + j);
                System.out.println("====== K= " + k + " ===== J= " + j);
                //  if (k >= j) {
                if (k > j) {
                    // String name = query.getString(0);
                    String name = query.getString(1);
                    String data = query.getString(2);

                    createObjectTwo();
                    my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                    my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText(data);
                    my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    //    int age = query.getInt(1);
                    //     textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("from second========================= " + j + " " + name);
                    System.out.println("from second========================= " + j + " " + data);
                    j++;


                }
            }
        }
/*
          while (query.moveToNext()) {
      //  while (query.moveToPosition(4)) {
            // вписать  createObjectTwo() и в коде задать значения для полей с задачей и вызывать
            // не по кнопке, а по умолчанию (при запуске экрана = Activity) из метода
            // protected void onCreate(Bundle savedInstanceState)
            //
            // сделать my_LinerLayout_01 для сегодня, завтра, после завтра, на неделе, потом
            // проходить циклом по дате для задачи и добавляем на соответствующий LinerLayout через
            //  my_LinerLayout_01.addView(my_txtView_from_List_Two.get(i).getMy_linearLayout());


            if (k >= j) {
                String name = query.getString(0);
                String data = query.getString(1);

                createObjectTwo();
                my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

               //    int age = query.getInt(1);
               //     textView.append("Name: " + name + " Age: " + age + "\n");
                System.out.println("from main========================= " + j + " " + name);
                System.out.println("from main========================= " + j + " " + data);
                j++;


            }

        }

        */

        query.close(); //закрываем связи
        db.close(); //закрываем связи


        ////
    }

    /*

        public void fill_Layout_for_tasks_02() {
            ////
            //int j = 0;
            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

            Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы

         //   k = query.getCount();
            System.out.println("==========k = query.getCount()= "+k);
            //  while (query.moveToNext()) {
            while (query.move(j)) {
                // вписать  createObjectTwo() и в коде задать значения для полей с задачей и вызывать
                // не по кнопке, а по умолчанию (при запуске экрана = Activity) из метода
                // protected void onCreate(Bundle savedInstanceState)
                //
                // сделать my_LinerLayout_01 для сегодня, завтра, после завтра, на неделе, потом
                // проходить циклом по дате для задачи и добавляем на соответствующий LinerLayout через
                //  my_LinerLayout_01.addView(my_txtView_from_List_Two.get(i).getMy_linearLayout());

               // if (k >= j) {

                    String name = query.getString(0);
                    String data = query.getString(1);

                    createObjectTwo();
                    my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                    my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    //   int age = query.getInt(1);
                    //    textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("from main========================= " + j + " " + name);
                    System.out.println("from main========================= " + j + " " + data);
                    j++;
            //    }

                query.close(); //закрываем связи
                db.close(); //закрываем связи
            }
            ////
        }
    */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void fill_Layout_for_tasks_03() { // для автоматического построения Layout в основном активити
        ////
        //  int j = 0;
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        //  Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы
        Cursor query = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы


        k = query.getCount();

        if (j == 0) {

            System.out.println("============= if(j==0) " + j);
            ///
            while (query.moveToNext()) {
                //  while (query.moveToPosition(4)) {
                // вписать  createObjectTwo() и в коде задать значения для полей с задачей и вызывать
                // не по кнопке, а по умолчанию (при запуске экрана = Activity) из метода
                // protected void onCreate(Bundle savedInstanceState)
                //
                // сделать my_LinerLayout_01 для сегодня, завтра, после завтра, на неделе, потом
                // проходить циклом по дате для задачи и добавляем на соответствующий LinerLayout через
                //  my_LinerLayout_01.addView(my_txtView_from_List_Two.get(i).getMy_linearLayout());


                int id_for_object = query.getInt(0);
                String name = query.getString(1);
                // String data = query.getString(1);
                ///////////
                long data_long = query.getLong(2);
                // boolean value_checkBox_from_DB = query.getExtras().getBoolean(String.valueOf(3));//2
                boolean value_checkBox_from_DB = Boolean.parseBoolean(query.getString(3));//2
                long done_data_fact = query.getLong(4);
                long data_long_time = query.getLong(5);

                boolean exist_subtask = Boolean.parseBoolean(query.getString(8));

                System.out.println("===data_long_time=== "+data_long_time);
                System.out.println("===exist_subtask=== "+exist_subtask);

                System.out.println("=====value_checkBox_from_DB=====" + value_checkBox_from_DB);

                //перевод из числа в строку формата дата
                Date date_obj = new Date();
                date_obj.setTime(data_long);
                String data_raw = date_obj.toString();
                String data = "";
                // форматируем дату по нужный нам вид
                // SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd ");
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("E'.' dd.MM.yy "); // описание http://proglang.su/java/date-and-time

                Date date_obj_parse = new Date();
                // date_obj_parse = formatForDateNow.parse(data_raw);
                //    date_obj_parse= formatForDateNow.format(data_raw);
                // data=date_obj_parse.toString();
                data = formatForDateNow.format(date_obj);


                //////
                createObjectTwo();
                my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText(data);
                my_txtView_from_List_Two.get(j).getMy_checkBox().setChecked(value_checkBox_from_DB);
                my_txtView_from_List_Two.get(j).setMy_task_id(id_for_object);

                if (data_long <= 0) { // если у задачи нет даты (дата = 01.01.1970 или data_long==0) то ничего в поле дата не пишем
                    my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText("");
                }

                System.out.println("=my_txtView_from_List_Two.get(j).setMy_task_id(id_for_object);= " +
                        my_txtView_from_List_Two.get(j).getMy_task_id());

///////////////////////////////////////////////// для нижней плашки колокольчик и список
                createObjectFour();
                if (data_long_time!=0) {
                      my_txtView_from_List_Four.get(j).setMy_have_notification(true);
                }

                SQLiteDatabase dbSubTask = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
                //  db.execSQL("UPDATE users_subtask_01 SET checkBox =  '" + value_check_subtask + "' WHERE _id='" + subtask_id + "'"); // обновление значения в базе

                Cursor query_subtask = dbSubTask.rawQuery("SELECT * FROM users_subtask_01;", null); // вытаскивает значения из базы
              // int j=0;

                while (query_subtask.moveToNext()) {

                    int id_from_db_subtask= query_subtask.getInt(0);
                    String text_subtask = query_subtask.getString(1);
                    boolean value_check = Boolean.parseBoolean(query_subtask.getString(2));
                    int id_from_db_parent= query_subtask.getInt(3);

                    //   int age = query.getInt(1);
                    //    textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("=========================id_from_db_subtask " + j + " " + id_from_db_subtask);
                    System.out.println("=========================text_subtask " + j + " " + text_subtask);
                    System.out.println("=========================value_check_subtask " + j + " " + value_check);
                    System.out.println("=========================id_from_db_parent===04 " + j + " " + id_from_db_parent);

                    if(id_from_db_parent==id_for_object) {
                        System.out.println("we are have a subtask");
                        my_txtView_from_List_Four.get(j).setMy_have_subtask(true);
                        System.out.println("my_txtView_from_List_Four.get(j).setMy_have_notification(true)"+
                                my_txtView_from_List_Four.get(j).getMy_have_subtask());
                    }

                //    j++;

                }
                query_subtask.close(); //закрываем связи
                dbSubTask.close();





                // отрисовка цвета для выполненых задач
                Resources resources = getResources();
                int textColor_checked = resources.getColor(R.color.my_color_for_checked, null);
                int textColor_black = resources.getColor(R.color.black, null);

                if (value_checkBox_from_DB == true) {
                    my_txtView_from_List_Two.get(j).getMy_textView().setTextColor(textColor_checked);
                } else {
                    my_txtView_from_List_Two.get(j).getMy_textView().setTextColor(textColor_black);
                }
                ///////

                ////обработчик изменений для checkBox

                // my_txtView_from_List_Two.get(j).getMy_checkBox().setChecked(true);
                check_box_listener(my_txtView_from_List_Two.get(j).getMy_checkBox(),
                        my_txtView_from_List_Two.get(j).getMy_textView(),
                        my_txtView_from_List_Two.get(j).getMy_task_id());
                ////////
                //обработчик textView для Задач
                task_listener(my_txtView_from_List_Two.get(j).getMy_textView(),
                        my_txtView_from_List_Two.get(j).getMy_textView_DATA(),
                        my_txtView_from_List_Two.get(j).getMy_task_id(),
                        data_long,data_long_time);




//////// заполнение Layout сегодня
                // получаем текущие дату
                // SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                SimpleDateFormat formatter = new SimpleDateFormat("E'.' dd.MM.yy HH:mm "); // класс для форматирования
                //  Date date = new Date(System.currentTimeMillis());
                Date current_date = new Date(); // при создании объекта автоматом задается текущая дата
                System.out.println("===========formatter.format(current_date)==" + formatter.format(current_date));

                // для отброса от текущей даты времени
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(current_date);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                current_date = calendar.getTime();

// для НА НЕДЕЛЕ
                SimpleDateFormat formatter_week = new SimpleDateFormat("w"); // класс для форматирования

                Date data_obj_week = date_obj;
                String num_of_current_week = formatter_week.format(current_date);
                String num_of_obj_week = formatter_week.format(data_obj_week);

                boolean eq_1 = num_of_current_week.equals(num_of_obj_week);

                System.out.println("======num_of_current_week===" + num_of_current_week);
                System.out.println("======data_obj_week===" + num_of_obj_week);
                System.out.println("======eq_1===" + eq_1);
/////
// для В ЭТОМ МЕСЯЦЕ
                SimpleDateFormat formatter_month = new SimpleDateFormat("M"); // класс для форматирования

                Date data_obj_month = date_obj;
                String num_of_current_month = formatter_month.format(current_date);
                String num_of_obj_month = formatter_month.format(data_obj_week);

                boolean eq_2 = num_of_current_week.equals(num_of_obj_week);

                System.out.println("======num_of_current_month===" + num_of_current_month);
                System.out.println("======data_obj_month===" + num_of_obj_month);
                System.out.println("======eq_2===" + eq_2);
/////


                //добавляем к текущей дате 1 день
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                // Date current_date_plus_day=new Date();
                Date current_date_plus_day = calendar.getTime();
                //  current_date_plus_day=calendar.getTime();


                System.out.println("====current_date_plus_day===" + formatter.format(current_date_plus_day));

                System.out.println("===========current_date=calendar.getTime();==" + formatter.format(current_date));
                System.out.println("===========current_date=calendar.getTime();==+==date_obj==" + formatter.format(date_obj));


                //  разделение по сегодня, завтра, на неделе, потом
                // запись о задаче должна быть только на одном layout иначе не работает

                if (data_long<0){  // убираем глюк при внесении изменений через Edit_Task_Activity
                    data_long=0;
                }


                if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                        (!(num_of_current_week.equals(num_of_obj_week))) &&
                        (!(num_of_current_month.equals(num_of_obj_month))) && (value_checkBox_from_DB == false)) {
                    //   my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                    my_LinerLayout_Late_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    System.out.println("========Date1 is after Date2======");

                    if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                    }
                    if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                    }
                    my_LinerLayout_Late_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());

                }

                if ((data_long == 0) && (value_checkBox_from_DB == false)) { // если у задачи нет даты то ее тоже в поле "Потом"
                    my_LinerLayout_Late_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                    }
                    if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                    }
                    my_LinerLayout_Late_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());

                    ///////

                  /////=  createObjectFour();
                 /////=   int sizeFour = my_txtView_from_List_Four.size()-1;
                   // my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().setVisibility(View.VISIBLE); // скрываем элемент
                   // my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().setVisibility(View.INVISIBLE);
                //    my_txtView_from_List_Four.get(sizeFour).getMy_textView().setVisibility(View.INVISIBLE);
                  //  my_txtView_from_List_Four.get(sizeFour).getMy_textView().setPadding(50,0,0,0);

                 //   my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().addView(my_txtView_from_List_Four.get(sizeFour).getMy_textView());  // work!  добавление, удаление элементов
                  //  my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().removeView(my_txtView_from_List_Four.get(sizeFour).getMy_textView());  // work!  добавление, удаление элементов

                   // my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().addView(my_txtView_from_List_Four.get(sizeFour).getMy_textView_02());  // work!  добавление, удаление элементов
                  //  my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().addView(my_txtView_from_List_Four.get(sizeFour).getMy_textView());  // work!  добавление, удаление элементов


                //////= my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().addView(my_txtView_from_List_Four.get(sizeFour).getMy_ImageView_01());  // work!  добавление, удаление элементов

               //////=    my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().addView(my_txtView_from_List_Four.get(sizeFour).getMy_ImageView_02());  // work!  добавление, удаление элементов



                    /*
                    ConstraintSet set = new ConstraintSet();

                    set.clear(my_txtView_from_List_Four.get(sizeFour).getMy_textView().getId(), ConstraintSet.LEFT);
                    set.clear(my_txtView_from_List_Four.get(sizeFour).getMy_textView().getId(), ConstraintSet.RIGHT);


                    set.connect(my_txtView_from_List_Four.get(sizeFour).getMy_textView().getId(), ConstraintSet.RIGHT,   // привязка
                          //  my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout().getId(), ConstraintSet.RIGHT);
                            ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
*/
                 /////=   my_LinerLayout_Late_01.addView(my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout());

                    ////////

                }


                if (date_obj.before(current_date) && (data_long != 0) && (value_checkBox_from_DB == false)) { // может сделать для просроченных отдельное поле???
                    my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    System.out.println("=======Date1 is before Date2=======");
                    System.out.println("=======add to layoyt TODAY=======");

////////////////////////////////////

                    if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                //        my_LinerLayout_Today_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                    }
                  //  if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                  //      my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                  //      my_LinerLayout_Today_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                 //   }
                 // my_txtView_from_List_Four.get(j).setMy_have_subtask(true)
                    if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                    }
                    my_LinerLayout_Today_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());

/////////////////////////////////


                }

                if (date_obj.equals(current_date) && (value_checkBox_from_DB == false)) {
                    my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    System.out.println("======Date1 is equal Date2=====");
                    if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                     //   my_LinerLayout_Today_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                    }
                    if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                    }
                    my_LinerLayout_Today_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());


                }

                if (date_obj.equals(current_date_plus_day) && (value_checkBox_from_DB == false)) {
                    my_LinerLayout_Tomorrow_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                    System.out.println("!!!====current_date_plus_day===!!!" + formatter.format(current_date_plus_day));

                    if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                    }
                    if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                    }
                    my_LinerLayout_Tomorrow_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                }

                if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                        ((num_of_current_week.equals(num_of_obj_week))) && (value_checkBox_from_DB == false)) {

                    my_LinerLayout_Week_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                    }
                    if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                    }
                    my_LinerLayout_Week_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                }

                if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                        (!(num_of_current_week.equals(num_of_obj_week))) &&
                        ((num_of_current_month.equals(num_of_obj_month))) && (value_checkBox_from_DB == false)) {

                    my_LinerLayout_Month_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                    }
                    if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                        my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                    }
                    my_LinerLayout_Month_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                }


//////// конец заполнение Layout сегодня, завтра, на неделе, в этом месяце потом

                //    int age = query.getInt(1);
                //     textView.append("Name: " + name + " Age: " + age + "\n");
                System.out.println("from first========================= " + j + " " + name);
                System.out.println("from first========================= " + j + " " + data);
                j++;


            }
            ///

        } else {
            while (query.move(j + 1)) {


                System.out.println("============= while (query.move(j)) " + j);
                System.out.println("====== K= " + k + " ===== J= " + j);
                //  if (k >= j) {
                if (k > j) {

                    int id_for_object = query.getInt(0);
                    String name = query.getString(1);
                    //   String data = query.getString(1);
                    ///////////
                    long data_long = query.getLong(2);
                    //   boolean value_checkBox_from_DB = query.getExtras().getBoolean(String.valueOf(3)); //2
                    boolean value_checkBox_from_DB = Boolean.parseBoolean(query.getString(3));//2
                    long done_data_fact = query.getLong(4);
                    long data_long_time = query.getLong(5);

                    System.out.println("=====value_checkBox_from_DB====2=" + value_checkBox_from_DB);


                    //перевод из числа в строку формата дата
                    Date date_obj = new Date();
                    date_obj.setTime(data_long);
                    String data_raw = date_obj.toString();
                    String data = "";
                    // форматируем дату по нужный нам вид
                    // SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd ");
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd "); // описание http://proglang.su/java/date-and-time

                    try {
                        Date date_obj_parse = new Date();
                        date_obj_parse = formatForDateNow.parse(data_raw);
                        data = date_obj_parse.toString();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("=======ошибка2======= mainActivity class");
                    }
                    //////


                    createObjectTwo();
                    my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                    my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText(data);
                    my_txtView_from_List_Two.get(j).getMy_checkBox().setChecked(value_checkBox_from_DB);
                    my_txtView_from_List_Two.get(j).setMy_task_id(id_for_object);

                    if (data_long <= 0) { // если у задачи нет даты (дата = 01.01.1970 или data_long==0) то ничего в поле дата не пишем
                        my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText("");
                    }

                    // my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    // отрисовка цвета для выполненых задач
                    Resources resources = getResources();
                    int textColor_checked = resources.getColor(R.color.my_color_for_checked, null);
                    int textColor_black = resources.getColor(R.color.black, null);

                    if (value_checkBox_from_DB == true) {
                        my_txtView_from_List_Two.get(j).getMy_textView().setTextColor(textColor_checked);
                    } else {
                        my_txtView_from_List_Two.get(j).getMy_textView().setTextColor(textColor_black);
                    }
                    ///////

                    ////обработчик изменений для checkBox

                    // my_txtView_from_List_Two.get(j).getMy_checkBox().setChecked(true);
                    check_box_listener(my_txtView_from_List_Two.get(j).getMy_checkBox(),
                            my_txtView_from_List_Two.get(j).getMy_textView(),
                            my_txtView_from_List_Two.get(j).getMy_task_id());
                    ////////
                    //обработчик textView для Задач
                    task_listener(my_txtView_from_List_Two.get(j).getMy_textView(),
                            my_txtView_from_List_Two.get(j).getMy_textView_DATA(),
                            my_txtView_from_List_Two.get(j).getMy_task_id(),
                            data_long,data_long_time);

//////// заполнение Layout сегодня
                    // получаем текущие дату
                    // SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    SimpleDateFormat formatter = new SimpleDateFormat("E'.' dd.MM.yy "); // класс для форматирования
                    //  Date date = new Date(System.currentTimeMillis());
                    Date current_date = new Date(); // при создании объекта автоматом задается текущая дата
                    System.out.println("===========formatter.format(current_date)==" + formatter.format(current_date));

                    // для отброса от текущей даты времени
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(current_date);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    current_date = calendar.getTime();

                    // для НА НЕДЕЛЕ
                    SimpleDateFormat formatter_wek = new SimpleDateFormat("w"); // класс для форматирования

                    Date data_obj_week = date_obj;
                    String num_of_current_week = formatter_wek.format(current_date);
                    String num_of_obj_week = formatter_wek.format(data_obj_week);

                    boolean eq_1 = num_of_current_week.equals(num_of_obj_week);

                    System.out.println("======num_of_current_week===" + num_of_current_week);
                    System.out.println("======data_obj_week===" + num_of_obj_week);
                    System.out.println("======eq_1===" + eq_1);
                    /////
// для В ЭТОМ МЕСЯЦЕ
                    SimpleDateFormat formatter_month = new SimpleDateFormat("M"); // класс для форматирования

                    Date data_obj_month = date_obj;
                    String num_of_current_month = formatter_month.format(current_date);
                    String num_of_obj_month = formatter_month.format(data_obj_week);

                    boolean eq_2 = num_of_current_week.equals(num_of_obj_week);

                    System.out.println("======num_of_current_month===" + num_of_current_month);
                    System.out.println("======data_obj_month===" + num_of_obj_month);
                    System.out.println("======eq_2===" + eq_2);
/////

                    //добавляем к текущей дате 1 день
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    // Date current_date_plus_day=new Date();
                    Date current_date_plus_day = calendar.getTime();
                    //  current_date_plus_day=calendar.getTime();


                    //  разделение по сегодня, завтра, на неделе, потом
                    // запись о задаче должна быть только на одном layout иначе не работает
                    // сделать для Потом отдельный Layout сейчас ложиться в общий
                    if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                            (!(num_of_current_week.equals(num_of_obj_week))) &&
                            (!(num_of_current_month.equals(num_of_obj_month))) && (value_checkBox_from_DB == false)) {
                        //   my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                        my_LinerLayout_Late_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        System.out.println("========Date1 is after Date2======");

                        if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                        }
                        if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                        }
                        my_LinerLayout_Late_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                    }

                    if ((data_long == 0) && (value_checkBox_from_DB == false)) { // если у задачи нет даты то ее тоже в поле "Потом"
                        my_LinerLayout_Late_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        ///////
                       // createObjectFour();
                       // int sizeFour = my_txtView_from_List_Four.size()-1;
                       // my_LinerLayout_Late_01.addView(my_txtView_from_List_Four.get(sizeFour).getMy_linearLayout());

                        ////////
                        if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                        }
                        if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                        }
                        my_LinerLayout_Late_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());

                    }


                    if (date_obj.before(current_date) && (value_checkBox_from_DB == false)) { // может сделать для просроченных отдельное поле???
                        my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        System.out.println("=======Date1 is before Date2=======");
                        System.out.println("=======add to layoyt TODAY=======");
                        if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                        }
                        if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                        }
                        my_LinerLayout_Today_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                    }


                    if (date_obj.equals(current_date) && (data_long != 0) && (value_checkBox_from_DB == false)) {
                        my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        System.out.println("======Date1 is equal Date2=====");
                        if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                        }
                        if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                        }
                        my_LinerLayout_Today_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                    }

                    if (date_obj.equals(current_date_plus_day) && (value_checkBox_from_DB == false)) {
                        my_LinerLayout_Tomorrow_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                        System.out.println("!!!====current_date_plus_day===!!!" + formatter.format(current_date_plus_day));

                        if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                        }
                        if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                        }
                        my_LinerLayout_Tomorrow_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                    }

                    if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                            ((num_of_current_week.equals(num_of_obj_week))) && (value_checkBox_from_DB == false)) {

                        my_LinerLayout_Week_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                        }
                        if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                        }
                        my_LinerLayout_Week_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                    }

                    if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                            (!(num_of_current_week.equals(num_of_obj_week))) &&
                            ((num_of_current_month.equals(num_of_obj_month))) && (value_checkBox_from_DB == false)) {

                        my_LinerLayout_Month_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        if( my_txtView_from_List_Four.get(j).getMy_have_notification()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_01());  // work!  добавление, удаление элементов
                        }
                        if( my_txtView_from_List_Four.get(j).getMy_have_subtask()==true) {  // плашка с колокольчиком и списком
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_textView_02());  // work!  добавление, удаление элементов
                            my_txtView_from_List_Four.get(j).getMy_linearLayout().addView(my_txtView_from_List_Four.get(j).getMy_ImageView_02());  // work!  добавление, удаление элементов
                        }
                        my_LinerLayout_Month_01.addView(my_txtView_from_List_Four.get(j).getMy_linearLayout());
                    }


//////// конец заполнение Layout сегодня, завтра, на неделе, в этом месяце потом


                    //    int age = query.getInt(1);
                    //     textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("from second========================= " + j + " " + name);
                    System.out.println("from second========================= " + j + " " + data);
                    j++;


                }
            }
        }
/*
          while (query.moveToNext()) {
      //  while (query.moveToPosition(4)) {
            // вписать  createObjectTwo() и в коде задать значения для полей с задачей и вызывать
            // не по кнопке, а по умолчанию (при запуске экрана = Activity) из метода
            // protected void onCreate(Bundle savedInstanceState)
            //
            // сделать my_LinerLayout_01 для сегодня, завтра, после завтра, на неделе, потом
            // проходить циклом по дате для задачи и добавляем на соответствующий LinerLayout через
            //  my_LinerLayout_01.addView(my_txtView_from_List_Two.get(i).getMy_linearLayout());


            if (k >= j) {
                String name = query.getString(0);
                String data = query.getString(1);

                createObjectTwo();
                my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

               //    int age = query.getInt(1);
               //     textView.append("Name: " + name + " Age: " + age + "\n");
                System.out.println("from main========================= " + j + " " + name);
                System.out.println("from main========================= " + j + " " + data);
                j++;


            }

        }

        */

        query.close(); //закрываем связи
        db.close(); //закрываем связи


        ////
    }
//////////////////


    public void check_box_listener(CheckBox checkBox, TextView textView, int task_id) {
        checkBox.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, R.string.hi_android, Toast.LENGTH_LONG).show();
                System.out.println("========checkBox.isChecked()=============================================3");
                System.out.println("========checkBox.isChecked()===================== " + task_id);

                Resources resources = getResources();
                int textColor_checked = resources.getColor(R.color.my_color_for_checked, null);
                int textColor_black = resources.getColor(R.color.black, null);
                // boolean value_checkBox_for_DB = checkBox.isChecked();
                boolean value_checkBox_for_DB;
                String string_name = (String) textView.getText();

                Date current_date_for_done = new Date(); // при создании объекта автоматом задается текущая дата


                if (checkBox.isChecked()) {
                    textView.setTextColor(textColor_checked);
                    value_checkBox_for_DB = true;
                } else {
                    textView.setTextColor(textColor_black);
                    value_checkBox_for_DB = false;

                }

                //// работа с базой

                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

                //  сделать в базе данных таблице users_06 столбец ID - автоинкремент для поиска записей
                //  db.execSQL("UPDATE users_06 SET value_of_checkBox = value_checkBox_for_DB WHERE id=...."); // добавление значения в базу

                // проверка - потом удалить
                Cursor query = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы
                while (query.moveToNext()) {
                    int id_from_db = query.getInt(0);
                    String name = query.getString(1);
                    String data = query.getString(2);
                    // boolean value_check = query.getExtras().getBoolean(String.valueOf(3));
                    //  int value_check = query.getInt(3);
                    boolean value_check = Boolean.parseBoolean(query.getString(3));


                    //   int age = query.getInt(1);
                    //    textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("=========================id_from_db " + i + " " + id_from_db);
                    System.out.println("=========================name " + i + " " + name);
                    System.out.println("=========================data " + i + " " + data);
                    System.out.println("=========================value_check " + i + " " + value_check);
                    i++;
                }
                query.close(); //закрываем связи


                //   db.execSQL("UPDATE users_06 SET checkBox = '" + value_checkBox_for_DB + "' WHERE _id='" + task_id + "'"); // обновление значения в базе
                //   db.execSQL("UPDATE users_06 SET name = ' бе-бе-бе ' WHERE _id='" + task_id + "'"); // обновление значения в базе
                db.execSQL("UPDATE users_07 SET checkBox =  '" + value_checkBox_for_DB + "', done_data_fact =  '" + current_date_for_done + "' WHERE _id='" + task_id + "'"); // обновление значения в базе


                System.out.println("=========после добавления в базу=========");

                Cursor query2 = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы
                i = 0;
                while (query2.moveToNext()) {
                    int id_from_db = query2.getInt(0);
                    String name = query2.getString(1);
                    String data = query2.getString(2);
                    String done_data = query2.getString(4);

                    //  boolean value_check = query2.getExtras().getBoolean(String.valueOf(3));
                    //  int value_check = query.getInt(3);
                    boolean value_check = Boolean.parseBoolean(query2.getString(3));


                    //   int age = query.getInt(1);
                    //    textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("=========================id_from_db " + i + " " + id_from_db);
                    System.out.println("=========================name " + i + " " + name);
                    System.out.println("=========================data " + i + " " + data);
                    System.out.println("=========================value_check " + i + " " + value_check);
                    System.out.println("=========================done_data_fact " + i + " " + done_data);
                    i++;
                }
                //  не работает обновление в базе данных


                query2.close(); //закрываем связи

                db.close(); //закрываем связи


             /////////////////////////// убираем сработку уведомление
                if (value_checkBox_for_DB==true) {
                    System.out.println("==save_Change==1==");

                    String St_id = String.valueOf(task_id);
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
                        String st_temp_for_true;
                        //  System.out.println("==subStrTime[0]== "+subStrTime[0]);
                        System.out.println("==subStrTime[0]=id= " + main_subStrTime[0]);


                        //   if (cur_Time_for_Notification==subStrTime[0]){
                        // if (cur_Time_for_Notification.equals(subStrTime[0])){  // сравниваем с первым элементом массива
                        if (St_id.equals(main_subStrTime[0])) {  // сравниваем с нулевым элементом массива (id)

                            st_temp_for_true=main_subStrTime[0]+"="+main_subStrTime[1]+"="+main_subStrTime[2]+"="+main_subStrTime[3]+"="+"true";
                            //  System.out.println("BINGO! "+"key =  " + item.getKey() + " value = " + item.getValue());
                            System.out.println("delete old " + "key =  " + item.getKey() + "/ value = " + main_subStrTime[3]);

                            prefEditor.remove(tempTime);
                            prefEditor.putString(st_temp_for_true, "empty");  //ключ, значение все записано в ключ

                            System.out.println("==main_key== /// " + st_temp_for_true);

                            prefEditor.apply();

                        }

                    }
                }
                if (value_checkBox_for_DB==false) {
                    System.out.println("==save_Change==1==");

                    String St_id = String.valueOf(task_id);
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
                        String st_temp_for_false;
                        //  System.out.println("==subStrTime[0]== "+subStrTime[0]);
                        System.out.println("==subStrTime[0]=id= " + main_subStrTime[0]);


                        //   if (cur_Time_for_Notification==subStrTime[0]){
                        // if (cur_Time_for_Notification.equals(subStrTime[0])){  // сравниваем с первым элементом массива
                        if (St_id.equals(main_subStrTime[0])) {  // сравниваем с нулевым элементом массива (id)

                            st_temp_for_false=main_subStrTime[0]+"="+main_subStrTime[1]+"="+main_subStrTime[2]+"="+main_subStrTime[3]+"="+"false";
                            //  System.out.println("BINGO! "+"key =  " + item.getKey() + " value = " + item.getValue());
                            System.out.println("delete old " + "key =  " + item.getKey() + "/ value = " + main_subStrTime[3]);

                            prefEditor.remove(tempTime);
                            prefEditor.putString(st_temp_for_false, "empty");  //ключ, значение все записано в ключ

                            System.out.println("==main_key== /// " + st_temp_for_false);

                            prefEditor.apply();

                        }

                    }
                }




                //////////////////////


            }


        });
    }
//////////////////////////



    public void task_listener(TextView textView, TextView TextView_Data, int task_id, long value_data, long value_data_time) {
        textView.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
             //   Toast.makeText(MainActivity.this, R.string.hi_android, Toast.LENGTH_LONG).show();
                startEditTaskActivity(textView);

                Edit_Task_Activity eta= new Edit_Task_Activity();
                eta.string_text_from_task= (String) textView.getText();


                Date date_object = new Date();
                Date date_object_time = new Date();
                date_object.setTime(value_data);
                date_object_time.setTime(value_data_time);
                String data_for_edit = "";
                String data_for_edit_time = "";

                SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy"); // описание http://proglang.su/java/date-and-time
                SimpleDateFormat formatForDateNow_time = new SimpleDateFormat("HH:mm"); // описание http://proglang.su/java/date-and-time
                data_for_edit = formatForDateNow.format(date_object);
                data_for_edit_time = formatForDateNow_time.format(date_object_time);
                eta.string_text_from_data=data_for_edit;
                eta.string_text_from_data_time=data_for_edit_time;

                if(value_data_time==0){
                    eta.string_text_from_data_time="";
                }
                if(value_data==0){
                    eta.string_text_from_data="";
                }

                eta.id_from_task=task_id;

                System.out.println("=========task listener=====!!!");
                /* System.out.println("========checkBox.isChecked()=============================================3");
                System.out.println("========checkBox.isChecked()===================== " + task_id);

                Resources resources = getResources();
                int textColor_checked = resources.getColor(R.color.my_color_for_checked, null);
                int textColor_black = resources.getColor(R.color.black, null);
                // boolean value_checkBox_for_DB = checkBox.isChecked();
                boolean value_checkBox_for_DB;
                String string_name = (String) textView.getText();

                if (checkBox.isChecked()) {
                    textView.setTextColor(textColor_checked);
                    value_checkBox_for_DB = true;
                } else {
                    textView.setTextColor(textColor_black);
                    value_checkBox_for_DB = false;

                }

                //// работа с базой

                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

                //  сделать в базе данных таблице users_06 столбец ID - автоинкремент для поиска записей
                //  db.execSQL("UPDATE users_06 SET value_of_checkBox = value_checkBox_for_DB WHERE id=...."); // добавление значения в базу

                // проверка - потом удалить
                Cursor query = db.rawQuery("SELECT * FROM users_06;", null); // вытаскивает значения из базы
                while (query.moveToNext()) {
                    int id_from_db = query.getInt(0);
                    String name = query.getString(1);
                    String data = query.getString(2);
                    // boolean value_check = query.getExtras().getBoolean(String.valueOf(3));
                    //  int value_check = query.getInt(3);
                    boolean value_check = Boolean.parseBoolean(query.getString(3));


                    //   int age = query.getInt(1);
                    //    textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("=========================id_from_db " + i + " " + id_from_db);
                    System.out.println("=========================name " + i + " " + name);
                    System.out.println("=========================data " + i + " " + data);
                    System.out.println("=========================value_check " + i + " " + value_check);
                    i++;
                }
                query.close(); //закрываем связи


                //   db.execSQL("UPDATE users_06 SET checkBox = '" + value_checkBox_for_DB + "' WHERE _id='" + task_id + "'"); // обновление значения в базе
                db.execSQL("UPDATE users_06 SET checkBox =  '" + value_checkBox_for_DB + "' WHERE _id='" + task_id + "'"); // обновление значения в базе
                //   db.execSQL("UPDATE users_06 SET name = ' бе-бе-бе ' WHERE _id='" + task_id + "'"); // обновление значения в базе


                System.out.println("=========после добавления в базу=========");

                Cursor query2 = db.rawQuery("SELECT * FROM users_06;", null); // вытаскивает значения из базы
                i = 0;
                while (query2.moveToNext()) {
                    int id_from_db = query2.getInt(0);
                    String name = query2.getString(1);
                    String data = query2.getString(2);
                    //  boolean value_check = query2.getExtras().getBoolean(String.valueOf(3));
                    //  int value_check = query.getInt(3);
                    boolean value_check = Boolean.parseBoolean(query2.getString(3));


                    //   int age = query.getInt(1);
                    //    textView.append("Name: " + name + " Age: " + age + "\n");
                    System.out.println("=========================id_from_db " + i + " " + id_from_db);
                    System.out.println("=========================name " + i + " " + name);
                    System.out.println("=========================data " + i + " " + data);
                    System.out.println("=========================value_check " + i + " " + value_check);
                    i++;
                }
                //  не работает обновление в базе данных


                query2.close(); //закрываем связи

                db.close(); //закрываем связи


*/
            }

        });
    }

    int q = 0;

    public void look_for_Notification (){  // сделать массив в который ложить записи у которых совпадает время вызова
                                           // потом этот массив прогнать циклом в NotificationReceiver
        System.out.println("==look_for_Notification==");
      //////
       // SimpleDateFormat formatter = new SimpleDateFormat("E'.' dd.MM.yy HH:mm "); // класс для форматирования
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm "); // класс для форматирования
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

        ArrayList<Long> Notification_List = new ArrayList<Long>(); // создание списка


      /////
/*

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



                if (name=="5"){
                   String stName_from_Main = name;
                 //   System.out.println("===data_long_time=== "+data_long_time);
                }


            }
        }


        query.close(); //закрываем связи
        db.close(); //закрываем связи
*/


    }


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

            Intent my_intent = new Intent(MainActivity.this, NotificationReceiver.class);  //должно быть так что бы работало
            // Intent my_intent = new Intent(eta_for_alarm2, NotificationReceiver.class);
            // PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            //PendingIntent pendingIntent =     PendingIntent.getBroadcast(Task_Activity.this,count_call_TA, my_intent,  PendingIntent.FLAG_ONE_SHOT);
            PendingIntent pendingIntent =     PendingIntent.getBroadcast(MainActivity.this,count_call_TA, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.this.ALARM_SERVICE);
          //  alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            alarmManager.set(AlarmManager.RTC_WAKEUP,puf,pendingIntent);



            i++;

        }


        query.close(); //закрываем связи
        db.close(); //закрываем связи

    }


}