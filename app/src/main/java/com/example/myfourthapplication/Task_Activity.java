package com.example.myfourthapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class Task_Activity extends AppCompatActivity {

    private Button Button_01;
    private EditText EditText_task_01;
    private EditText EditText_task_02;
    private EditText EditText_task_03;

    private ScrollView ScrollView_task_01;
    private LinearLayout LinearLayout_task_01;
    static long last_id_from_task_TA;
    int count_call_TA;
    //  private CheckBox CheckBox_task_01;
    int counter=0;
    String st_data_notif_TA ="";
    String st_time_notif_TA ="";

    boolean value_of_main_checkBox_TA = false;
    String checkBox_notif= String.valueOf(value_of_main_checkBox_TA);

    Calendar calendar = Calendar.getInstance();
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Button_01 = findViewById(R.id.button_task_01);
        EditText_task_01 = findViewById(R.id.EditText_task_01_xml);
        EditText_task_02 = findViewById(R.id.EditText_task_data_02_xml);
        EditText_task_03 = findViewById(R.id.EditText_edit_task_data_04_xml);

      //  CheckBox_task_01 = findViewById(R.id.checkBox_1);   //вроде он здесь не нужен, при создании задачи значение всегда будет false

     //   ScrollView_task_01= findViewById(R.id.scrollView_task_01_xml);
        LinearLayout_task_01= findViewById(R.id.LinearLayout_scroll_02_xml);


        showToastFunction_01(Button_01);// обработка нажатия

        //   createObjectThreeForSubtask();
      //  addSubtask();

    }


    public void showToastFunction_01(Button value) { // показывает всплывающее сообщение
        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     Toast.makeText(Task_Activity.this, R.string.hi_android, Toast.LENGTH_LONG).show();

                onClick_01();
             //   saveSubtask();
                save_Change(calendar); // сохраняет насройки и для в рессивера
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
    boolean exist_subtask_true = true;
    boolean exist_subtask_false = false;
  //  boolean value_of_checkBox =true;


    // public void onClick(View view){
    public void onClick_01() {

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
        System.out.println("===============data format long from String_all_value===== " + str3);
        System.out.println("===============data format long from String===== " + a5);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("=======ошибка=======");
        }

        try {
            Date docDate_2 = format_for_a6_time.parse(str3_time);
            a6_time = docDate_2.getTime();
            System.out.println("===============data format long from String_all_value_time===== " + str3_time);
            System.out.println("===============data format long from String_time===== " + a6_time);
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

       // db.execSQL("CREATE TABLE IF NOT EXISTS users_06 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,data INTEGER,checkBox BOOL, UNIQUE(name))");//создание таблицы users_01
      //  db.execSQL("CREATE TABLE IF NOT EXISTS users_06 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,data INTEGER,checkBox BOOL)");//создание таблицы users_01 , UNIQUE - не нужен
      //  db.execSQL("DROP TABLE IF EXISTS  users_07"); //удаление таблицы

        db.execSQL("CREATE TABLE IF NOT EXISTS users_07 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
                "data INTEGER,checkBox BOOL,done_data_fact INTEGER,time_alert INTEGER,exist_alert BOOL," +
                "exist_important BOOL,exist_subtask BOOL)");//создание таблицы users_01 , UNIQUE - не нужен

        // поле "name" в ней текстовое и уникальное (UNIQUE(name)) но это не точно:-)

        //   db.execSQL("INSERT OR IGNORE INTO users_01 VALUES ('" + a2 + "');"); // добавление значения в базу
        //   db.execSQL("INSERT OR IGNORE INTO users_02 VALUES ('" + a2 + "','" + a4 + "');"); // добавление значения в базу
       //  db.execSQL("INSERT OR IGNORE INTO users_07 (name, data, checkBox,exist_subtask) VALUES ('" + a2 + "','" + a5 + "','" + value_of_checkBox + "','" + exist_subtask_false + "');"); // добавление значения в базу
        db.execSQL("INSERT OR IGNORE INTO users_07 (name, data, checkBox,exist_subtask,time_alert) VALUES ('" + a2 + "','" + a5 + "','" + value_of_checkBox + "','" + exist_subtask_false + "','" + a6_time + "');"); // добавление значения в базу

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
            String alarm_time = query.getString(5);

            last_id_from_task_TA=id_from_db;
            count_call_TA= (int)last_id_from_task_TA;
            //   int age = query.getInt(1);
            //    textView.append("Name: " + name + " Age: " + age + "\n");
            System.out.println("=========================id_from_db " + i + " " + id_from_db);
            System.out.println("=========================name " + i + " " + name);
            System.out.println("=========================data " + i + " " + data);
            System.out.println("=========================value_check=main " + i + " " + value_check);
            System.out.println("=========================done_data_fact " + i + " " + done_data_fact);
            System.out.println("=========================alarm_time " + i + " " + alarm_time);

            i++;

        }


        query.close(); //закрываем связи
        db.close(); //закрываем связи

        /////

        DB_for_subtask();
        ////
    }

    public void DB_for_subtask() {

       int array_size = my_txtView_from_Subtask_List_Three.size();
        System.out.println("array_size = "+ array_size);

        if (array_size!=0){

            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS users_subtask_01 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
                    "checkBox BOOL,parent_task_id INTEGER)");//создание таблицы users_01 , UNIQUE - не нужен

         //   db.execSQL("INSERT OR IGNORE INTO users_07 (exist_subtask) VALUES ('" + exist_subtask_true + "');"); // добавление значения в базу



            Cursor query = db.rawQuery("SELECT * FROM users_07;", null); // вытаскивает значения из базы
            int last_parent_id=0;
            while (query.moveToNext()) {
                last_parent_id= query.getInt(0);
                System.out.println("last_parent_id = "+last_parent_id);
            }
            query.close(); //закрываем связи

           db.execSQL("UPDATE users_07 SET exist_subtask =  '" + exist_subtask_true + "' WHERE _id='" + last_parent_id + "'"); // обновление значения в базе
        //    db.execSQL("UPDATE users_07 SET exist_subtask =  '" + exist_subtask_true + "' WHERE _id=1"); // обновление значения в базе


            for (int i = 0; i < my_txtView_from_Subtask_List_Three.size(); i++) {
                System.out.println(my_txtView_from_Subtask_List_Three.get(i).getMy_textView());

             Editable text_from_subtask_textView = (Editable) my_txtView_from_Subtask_List_Three.get(i).getMy_textView().getText();
             String text_from_subtask = String.valueOf(text_from_subtask_textView); // перевод из формата от TextView в формат String
             boolean value_of_checkBox =  my_txtView_from_Subtask_List_Three.get(i).getMy_checkBox().isChecked();

              db.execSQL("INSERT OR IGNORE INTO users_subtask_01 (name, checkBox, parent_task_id) VALUES ('" + text_from_subtask + "','" + value_of_checkBox + "','" + last_parent_id + "');"); // добавление значения в базу



                Cursor query_subtask = db.rawQuery("SELECT * FROM users_subtask_01;", null); // вытаскивает значения из базы
                int j=0;

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
                    System.out.println("=========================id_from_db_parent " + j + " " + id_from_db_parent);

                    j++;

                }
                query_subtask.close(); //закрываем связи


            }

            db.close(); //закрываем связи
        }
    }




        ArrayList<Three> my_txtView_from_Subtask_List_Three = new ArrayList<Three>(); // создание списка который
        // будет содержать в себе значения типа "Three"

    public void createObjectThreeForSubtask() {

        int idTask = 0;

        Three my_three_01 = new Three(new EditText(this), new TextView(this),
                new CheckBox(this), new LinearLayout(this), idTask);

        my_txtView_from_Subtask_List_Three.add(my_three_01);
    }



    public void addSubtask(){
       // ScrollView_task_01.addView(my_txtView_from_Subtask_List_Three.get(0).getMy_linearLayout());
        my_txtView_from_Subtask_List_Three.get(0).getMy_textView().setText("text-1");
        LinearLayout_task_01.addView(my_txtView_from_Subtask_List_Three.get(0).getMy_linearLayout());
        System.out.println("=======check=======12.06.2022-1"+
                String.valueOf(my_txtView_from_Subtask_List_Three.get(0).getMy_textView().getText()));
/*
        createObjectTwoForSubtask();
        System.out.println("=======check=======12.06.2022-2");

        my_txtView_from_Subtask_List_Three.get(1).getMy_textView().setText("text-2");
        LinearLayout_task_01.addView(my_txtView_from_Subtask_List_Three.get(1).getMy_linearLayout());
        System.out.println("=======check=======12.06.2022-3"+
                String.valueOf(my_txtView_from_Subtask_List_Three.get(1).getMy_textView().getText()));
        */
    }


    public void addSubtask2(View view){
        /*
        System.out.println("===addSubtask2==1");
        createObjectThreeForSubtask();
        System.out.println("===addSubtask2==2");
        // ScrollView_task_01.addView(my_txtView_from_Subtask_List_Three.get(0).getMy_linearLayout());
        LinearLayout_task_01.addView(my_txtView_from_Subtask_List_Three.get(1).getMy_linearLayout());
        System.out.println("===addSubtask2==3");
*/
        createObjectThreeForSubtask();
        LinearLayout_task_01.addView(my_txtView_from_Subtask_List_Three.get(counter).getMy_linearLayout());

/*

        if (counter==5) {
          //  LinearLayout_task_01.removeAllViewsInLayout();
            LinearLayout_task_01.removeView(my_txtView_from_Subtask_List_Three.get(1).getMy_linearLayout());
        }
*/

        subtask_listener(my_txtView_from_Subtask_List_Three.get(counter).getMy_textView_DATA(),counter);

        counter++;
    }




    public void saveSubtask(){
      //  Editable str_Editable= (Editable) my_txtView_from_Subtask_List_Three.get(0).getMy_textView().getText();
     // String str_from_editText= String.valueOf(str_Editable);
      String str_from_editText= String.valueOf(my_txtView_from_Subtask_List_Three.get(0).getMy_textView().getText());
      //  my_txtView_from_Subtask_List_Two.get(0).getMy_textView();
        System.out.println("==========str_from_editText======="+str_from_editText);

    }

    public void subtask_listener(TextView TextView_Data, int counter_met) {

        TextView_Data.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View TextView_Data) {
            LinearLayout_task_01.removeView(my_txtView_from_Subtask_List_Three.get(counter_met).getMy_linearLayout());
            my_txtView_from_Subtask_List_Three.remove(counter_met);
            }
        });
    }


    public void startChooseData_TA(View view)  {
        System.out.println("startChooseData()-1");
        callDatePicker();

        System.out.println("startChooseData()-2");



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


        new DatePickerDialog(Task_Activity.this, d,
                calendar_for_picker.get(Calendar.YEAR),
                calendar_for_picker.get(Calendar.MONTH),
                calendar_for_picker.get(Calendar.DAY_OF_MONTH))
                .show();

    }



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
           // Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
            calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
            /////// проверка при добавлении даты
            calendar.set(Calendar.YEAR, calendar_for_picker.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, calendar_for_picker.get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, calendar_for_picker.get(Calendar.DAY_OF_MONTH));



            Toast.makeText(this, "Будильник установлен на " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();

            /*
            Intent my_intent = new Intent(getApplicationContext(), NotificationReceiver.class);
            PendingIntent pendingIntent =    PendingIntent.getBroadcast(Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
*/


/*
//=============
            Intent my_intent = new Intent(Task_Activity.this, NotificationReceiver.class);  //должно быть так что бы работало
            // Intent my_intent = new Intent(eta_for_alarm2, NotificationReceiver.class);
            // PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntent =     PendingIntent.getBroadcast(Task_Activity.this,count_call_TA, my_intent,  PendingIntent.FLAG_ONE_SHOT);
        //    PendingIntent pendingIntent =     PendingIntent.getBroadcast(Task_Activity.this,3, my_intent,  PendingIntent.FLAG_ONE_SHOT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Task_Activity.this.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

*/





            st_time_notif_TA = sdf_for_EditText_Time.format(calendar.getTime());
            st_data_notif_TA =sdf_for_EditText.format(calendar.getTime());

            /////установка значения в EditText
            EditText_task_02.setText(sdf_for_EditText.format(calendar.getTime()));
            EditText_task_03.setText(sdf_for_EditText_Time.format(calendar.getTime()));

          //  instance_callAlarmManager2 (calendar, Task_Activity.this);


        });


        materialTimePicker.show(getSupportFragmentManager(), "tag_picker");



    }


    public void save_Change(Calendar calendar) {

       // String St_id = String.valueOf(count_call_TA);
        settings = getSharedPreferences("PreferencesName2", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();


        ////////////  удаляет из настроек ранее созданную запись, если такая была создана ранее
     //   Map<String, ?> my_Map = settings.getAll();

      //  System.out.println("==first all == " + my_Map);

        //перебор мапы
        //попробовать собрать трехмерный массив и с ним работать
     /*   for (Map.Entry<String, ?> item : my_Map.entrySet()) {

            String tempTime = item.getKey();
            String delimeter = "=";
            //String[] subStrTime = tempTime.split(delimeter, 2); //массив  разбивает на 2 части
            String[] main_subStrTime = tempTime.split(delimeter, 4); //массив  разбивает на 4 части
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
*/
        //////////////// создает новыю запись в настройках

        if(!(EditText_task_03.getText().toString().equals(""))) {

            Intent my_intent = new Intent(Task_Activity.this, NotificationReceiver.class);  //должно быть так что бы работало
            // Intent my_intent = new Intent(eta_for_alarm2, NotificationReceiver.class);
            // PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            //PendingIntent pendingIntent =     PendingIntent.getBroadcast(Task_Activity.this,count_call_TA, my_intent,  PendingIntent.FLAG_ONE_SHOT);

            System.out.println("====count_call_TA=== " + count_call_TA);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Task_Activity.this, count_call_TA, my_intent, PendingIntent.FLAG_ONE_SHOT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Task_Activity.this.ALARM_SERVICE);
            System.out.println("====calendar.getTimeInMillis()===x3== " + calendar.getTimeInMillis());
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            //  alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

            ////
            //  pendingIntent =     PendingIntent.getBroadcast(Task_Activity.this,(count_call_TA-1), my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            //  alarmManager = (AlarmManager) getSystemService(Task_Activity.this.ALARM_SERVICE);
            //  alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            ////

            //////////////////
            // сохраняем его в настройках
            String key = String.valueOf(count_call_TA);


            // String id="44";
            String id = String.valueOf(count_call_TA);
            // String time="00:44";
            //  String time=cur_Time_for_Notification;
            String time = st_time_notif_TA;
            // String time=String.valueOf(EditText_task_03.getText());
            // String data="04.07.2022";
            // String data=st_data_notif;
            // String data=String.valueOf(EditText_task_02.getText());
            String data = st_data_notif_TA;
            //  String task="task N100500";
            // String task=string_text_from_task;
            String task = String.valueOf(EditText_task_01.getText());

            String checkBox = checkBox_notif;


            String main_key = id + "=" + time + "=" + data + "=" + task+ "=" + checkBox;

            prefEditor.putString(main_key, "empty");  //ключ, значение все записано в ключ

            System.out.println("==add main_key== /// " + main_key);

            prefEditor.apply();
        }

    }

    public void resetTime_TA(View view){

        EditText_task_03.setText("");

    }
    public void resetData_TA(View view){

        EditText_task_02.setText("");
        EditText_task_03.setText("");
    }
}