package com.example.myfourthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton ImageButton_01;
    private ImageButton ImageButton_02;
    private ImageButton ImageButton_03;
    private ImageButton ImageButton_04;

    private LinearLayout my_LinerLayout_01;

    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);//присвоение ранее созданной переменной
        // типа LinearLayout конкретного значения LinearLayout из XML файла
        ImageButton_01 = findViewById(R.id.ImageButton_xml_01);
        ImageButton_02 = findViewById(R.id.ImageButton_xml_02);
        ImageButton_03 = findViewById(R.id.ImageButton_xml_03);
        ImageButton_04 = findViewById(R.id.ImageButton_xml_04);

        showToastFunction_01(ImageButton_01);// обработка нажатия
        showToastFunction_01(ImageButton_02);
        showToastFunction_01(ImageButton_03);
        // showToastFunction_01(ImageButton_04);

        // add_task_04(ImageButton_04); // добавление новой задачи
      //  fill_Layout_for_tasks_02();
    }

    public void showToastFunction_01(ImageButton value) { // показывает всплывающее сообщение
        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.hi_android, Toast.LENGTH_LONG).show();
            }
        });
    }

    // открытие другого окна (activity)
    // метод вызывается с помощью прописанного в xml файле "activity_main.xml" для
    // кнопки Button ("@+id/button3") и кнопки ImageButton ("@+id/ImageButton_xml_04")
    // атрибута android:onClick="startTaskActivity"
    public void startTaskActivity(View view) {
        Intent intent = new Intent(this, Task_Activity.class);
        startActivity(intent);

        ////

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

    ArrayList<Two> my_txtView_from_List_Two = new ArrayList<Two>(); // создание списка который
    // будет содержать в себе значения типа "Two"

    // метод который инициирует создание объекта (класа) Two с необходимыми для работы атрибутами
    // после создания ложит экземпляры созданных объектов в список. Работаем со списком через переменную "int i=0"
    public void createObjectTwo() {
        Two my_two_01 = new Two(new TextView(this), new TextView(this),
                new CheckBox(this), new LinearLayout(this));
        my_txtView_from_List_Two.add(my_two_01);
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

        Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы


        k = query.getCount();

        if(j==0){

            System.out.println("============= if(j==0) "+j);
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


                //if (k >= j) {
                if (k > j) {
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
            ///

        }
        else{
            while (query.move(j)) {


                System.out.println("============= while (query.move(j)) "+j);

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

}