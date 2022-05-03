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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageButton ImageButton_01;
    private ImageButton ImageButton_02;
    private ImageButton ImageButton_03;
    private ImageButton ImageButton_04;

    private LinearLayout my_LinerLayout_01;
    private LinearLayout my_LinerLayout_Today_01;

    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);//присвоение ранее созданной переменной
        my_LinerLayout_Today_01 = findViewById(R.id.my_LinerLayout_Today_xml_01);//присвоение ранее созданной переменной
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
        create_BD_01();//  создаем базу данных


        fill_Layout_for_tasks_03();
    }

    public void create_BD_01() { //  создаем базу данных
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS users_03 (name TEXT,data INTEGER, UNIQUE(name))");//создание таблицы users_01
        // поле "name" в ней текстовое и уникальное (UNIQUE(name)) но это не точно:-)

        db.close(); //закрываем связи
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

      //  Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы
        Cursor query = db.rawQuery("SELECT * FROM users_03;", null); // вытаскивает значения из базы


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


                String name = query.getString(0);
                String data = query.getString(1);

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
                    String name = query.getString(0);
                    String data = query.getString(1);

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
    public void fill_Layout_for_tasks_03() { // для автоматического построения Layout в основном активити
        ////
        //  int j = 0;
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

      //  Cursor query = db.rawQuery("SELECT * FROM users_02;", null); // вытаскивает значения из базы
        Cursor query = db.rawQuery("SELECT * FROM users_03;", null); // вытаскивает значения из базы


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


                String name = query.getString(0);
               // String data = query.getString(1);
             ///////////
                long data_long = query.getLong(1);

                //перевод из числа в строку формата дата
                Date date_obj = new Date();
                date_obj.setTime(data_long);
                String data_raw=date_obj.toString();
                String data = "";
                // форматируем дату по нужный нам вид
               // SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd ");
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("E'.' dd.MM.yy "); // описание http://proglang.su/java/date-and-time

                Date date_obj_parse = new Date();
                // date_obj_parse = formatForDateNow.parse(data_raw);
                //    date_obj_parse= formatForDateNow.format(data_raw);
                // data=date_obj_parse.toString();
                data=formatForDateNow.format(date_obj);


                //////
                createObjectTwo();
                my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText(data);
             //  my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

//////// заполнение Layout сегодня
                // получаем текущие дату
               // SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                SimpleDateFormat formatter= new SimpleDateFormat("E'.' dd.MM.yy "); // класс для форматирования
              //  Date date = new Date(System.currentTimeMillis());
                Date current_date = new Date(); // при создании объекта автоматом задается текущая дата
                System.out.println("===========formatter.format(current_date)=="+formatter.format(current_date));

              //  разделение по сегодня, завтра, на неделе, потом
              // запись о задаче должна быть только на одном layout иначе не работает
                if(date_obj.after(current_date)){
                    my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                    System.out.println("========Date1 is after Date2======");
                }

                if(date_obj.before(current_date)){
                    my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    System.out.println("=======Date1 is before Date2=======");
                    System.out.println("=======add to layoyt TODAY=======");

                }

                if(date_obj.equals(current_date)){
                    System.out.println("======Date1 is equal Date2=====");
                }

//////// конец заполнение Layout сегодня

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
                    String name = query.getString(0);
                 //   String data = query.getString(1);
                    ///////////
                    long data_long = query.getLong(1);

                    //перевод из числа в строку формата дата
                    Date date_obj = new Date();
                    date_obj.setTime(data_long);
                    String data_raw=date_obj.toString();
                    String data = "";
                    // форматируем дату по нужный нам вид
                    // SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd ");
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd "); // описание http://proglang.su/java/date-and-time

                    try {
                        Date date_obj_parse = new Date();
                        date_obj_parse = formatForDateNow.parse(data_raw);
                        data=date_obj_parse.toString();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("=======ошибка2======= mainActivity class");
                    }
                    //////


                    createObjectTwo();
                    my_txtView_from_List_Two.get(j).getMy_textView().setText(name);
                    my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText(data);
                   // my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
//////// заполнение Layout сегодня
                    // получаем текущие дату
                    // SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    SimpleDateFormat formatter= new SimpleDateFormat("E'.' dd.MM.yy "); // класс для форматирования
                    //  Date date = new Date(System.currentTimeMillis());
                    Date current_date = new Date(); // при создании объекта автоматом задается текущая дата
                    System.out.println("===========formatter.format(current_date)=="+formatter.format(current_date));

                    //  разделение по сегодня, завтра, на неделе, потом
                    // запись о задаче должна быть только на одном layout иначе не работает
                    // сделать для Потом отдельный Layout сейчас ложиться в общий
                    if(date_obj.after(current_date)){
                        my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                        System.out.println("========Date1 is after Date2======");
                    }

                    if(date_obj.before(current_date)){
                        my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        System.out.println("=======Date1 is before Date2=======");
                        System.out.println("=======add to layoyt TODAY=======");
                    }

                    if(date_obj.equals(current_date)){

                    }

//////// конец заполнение Layout сегодня
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

}