package com.example.myfourthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task_Activity extends AppCompatActivity {

    private Button Button_01;
    private EditText EditText_task_01;
    private EditText EditText_task_02;
  //  private CheckBox CheckBox_task_01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Button_01 = findViewById(R.id.button_task_01);
        EditText_task_01 = findViewById(R.id.EditText_task_01_xml);
        EditText_task_02 = findViewById(R.id.EditText_task_data_02_xml);
      //  CheckBox_task_01 = findViewById(R.id.checkBox_1);   //вроде он здесь не нужен, при создании задачи значение всегда будет false
        showToastFunction_01(Button_01);// обработка нажатия

    }


    public void showToastFunction_01(Button value) { // показывает всплывающее сообщение
        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Task_Activity.this, R.string.hi_android, Toast.LENGTH_LONG).show();

                onClick_01();
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

  //  boolean value_of_checkBox = false;
    boolean value_of_checkBox =true;


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
        db.execSQL("CREATE TABLE IF NOT EXISTS users_06 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,data INTEGER,checkBox BOOL)");//создание таблицы users_01 , UNIQUE - не нужен
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

            //   int age = query.getInt(1);
            //    textView.append("Name: " + name + " Age: " + age + "\n");
            System.out.println("=========================id_from_db " + i + " " + id_from_db);
            System.out.println("=========================name " + i + " " + name);
            System.out.println("=========================data " + i + " " + data);
            System.out.println("=========================value_check=main " + i + " " + value_check);

            i++;
        }
        query.close(); //закрываем связи
        db.close(); //закрываем связи

        /////


        ////
    }
}