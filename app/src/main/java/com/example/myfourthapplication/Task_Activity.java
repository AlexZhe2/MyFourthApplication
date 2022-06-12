package com.example.myfourthapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.ls.LSOutput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Task_Activity extends AppCompatActivity {

    private Button Button_01;
    private EditText EditText_task_01;
    private EditText EditText_task_02;

    private ScrollView ScrollView_task_01;
    private LinearLayout LinearLayout_task_01;

  //  private CheckBox CheckBox_task_01;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Button_01 = findViewById(R.id.button_task_01);
        EditText_task_01 = findViewById(R.id.EditText_task_01_xml);
        EditText_task_02 = findViewById(R.id.EditText_task_data_02_xml);
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
                saveSubtask();
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
    long a3 = System.currentTimeMillis();
    long a5;
    Editable a2;
    Editable a4;

    boolean value_of_checkBox = false;
  //  boolean value_of_checkBox =true;


    // public void onClick(View view){
    public void onClick_01() {

        a2 = EditText_task_01.getText();
        a4 = EditText_task_02.getText();

        str2 = String.valueOf(a4);
        str3 = String.valueOf(a4); // перевод из формата от TextView в формат String

       // value_of_checkBox =CheckBox_task_01.isChecked();

/////////////////
        //перевод из строки в число
        //   String s="05.09.2013";  // нужный формат
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            Date docDate = format.parse(str3);
            a5 = docDate.getTime();
            System.out.println("===============data format long from String===== " + a5);
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
       // db.execSQL("DROP TABLE IF EXISTS  users_06"); //удаление таблицы

        db.execSQL("CREATE TABLE IF NOT EXISTS users_06 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
                "data INTEGER,checkBox BOOL,done_data_fact INTEGER,time_alert INTEGER,exist_alert BOOL," +
                "exist_important BOOL)");//создание таблицы users_01 , UNIQUE - не нужен

        // поле "name" в ней текстовое и уникальное (UNIQUE(name)) но это не точно:-)

        //   db.execSQL("INSERT OR IGNORE INTO users_01 VALUES ('" + a2 + "');"); // добавление значения в базу
        //   db.execSQL("INSERT OR IGNORE INTO users_02 VALUES ('" + a2 + "','" + a4 + "');"); // добавление значения в базу
        db.execSQL("INSERT OR IGNORE INTO users_06 (name, data, checkBox) VALUES ('" + a2 + "','" + a5 + "','" + value_of_checkBox + "');"); // добавление значения в базу

        Cursor query = db.rawQuery("SELECT * FROM users_06;", null); // вытаскивает значения из базы

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
            }
        });
    }




}