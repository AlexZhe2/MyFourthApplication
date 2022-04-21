package com.example.myfourthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Task_Activity extends AppCompatActivity {

    private Button Button_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Button_01 = findViewById(R.id.button_task_01);
        showToastFunction_01(Button_01);// обработка нажатия

    }

    public void showToastFunction_01(Button value) { // показывает всплывающее сообщение
        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Task_Activity.this, R.string.hi_android, Toast.LENGTH_LONG).show();

                onClick_01();
            }
        });
    }

    public void startMainActivity(View view) {
       /* Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
    }
    //  чтобы кнопка "Сохранить" всегда была видна и не закрывалась всплывающей
    //  клавиатурой в файле AndroidManifest.xml в соответствующем разделе <activity>
    //  добавлена строка android:windowSoftInputMode="adjustResize"

    //База данных
    String a1 ="Alex Zhe";
   // public void onClick(View view){
    public void onClick_01(){

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS users_01 (name TEXT, UNIQUE(name))");//создание таблицы users_01
        // поле "name" в ней текстовое и уникальное (UNIQUE(name))

        db.execSQL("INSERT OR IGNORE INTO users_01 VALUES ('"+a1+"');"); // добавление значения в базу

        Cursor query = db.rawQuery("SELECT * FROM users_01;", null); // вытаскивает значения из базы

        //  TextView textView = findViewById(R.id.textView);
      //  textView.setText("");
        int i=0;
        while(query.moveToNext()){
            String name = query.getString(0);
         //   int age = query.getInt(1);
        //    textView.append("Name: " + name + " Age: " + age + "\n");
            System.out.println("========================= "+i+" "+ name);
            i++;
        }
        query.close(); //закрываем связи
        db.close(); //закрываем связи
    }
}