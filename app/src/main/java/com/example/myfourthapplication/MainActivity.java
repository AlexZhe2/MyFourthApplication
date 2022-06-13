package com.example.myfourthapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
         // db.execSQL("DROP TABLE IF EXISTS  users_07"); //удаление таблицы
        // db.execSQL("DROP TABLE IF EXISTS  users_subtask_01"); //удаление таблицы

        db.execSQL("CREATE TABLE IF NOT EXISTS users_07 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
                "data INTEGER,checkBox BOOL,done_data_fact INTEGER,time_alert INTEGER,exist_alert BOOL," +
                "exist_important BOOL,exist_subtask BOOL)");//создание таблицы users_01 , UNIQUE - не нужен


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

                if (data_long == 0) { // если у задачи нет даты (дата = 01.01.1970 или data_long==0) то ничего в поле дата не пишем
                    my_txtView_from_List_Two.get(j).getMy_textView_DATA().setText("");
                }

                System.out.println("=my_txtView_from_List_Two.get(j).setMy_task_id(id_for_object);= " +
                        my_txtView_from_List_Two.get(j).getMy_task_id());

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
                        data_long);




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
                if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                        (!(num_of_current_week.equals(num_of_obj_week))) &&
                        (!(num_of_current_month.equals(num_of_obj_month))) && (value_checkBox_from_DB == false)) {
                    //   my_LinerLayout_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                    my_LinerLayout_Late_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    System.out.println("========Date1 is after Date2======");
                }

                if ((data_long == 0) && (value_checkBox_from_DB == false)) { // если у задачи нет даты то ее тоже в поле "Потом"
                    my_LinerLayout_Late_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                }


                if (date_obj.before(current_date) && (data_long != 0) && (value_checkBox_from_DB == false)) { // может сделать для просроченных отдельное поле???
                    my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    System.out.println("=======Date1 is before Date2=======");
                    System.out.println("=======add to layoyt TODAY=======");

                }

                if (date_obj.equals(current_date) && (value_checkBox_from_DB == false)) {
                    my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                    System.out.println("======Date1 is equal Date2=====");
                }

                if (date_obj.equals(current_date_plus_day) && (value_checkBox_from_DB == false)) {
                    my_LinerLayout_Tomorrow_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                    System.out.println("!!!====current_date_plus_day===!!!" + formatter.format(current_date_plus_day));

                }

                if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                        ((num_of_current_week.equals(num_of_obj_week))) && (value_checkBox_from_DB == false)) {

                    my_LinerLayout_Week_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                }

                if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                        (!(num_of_current_week.equals(num_of_obj_week))) &&
                        ((num_of_current_month.equals(num_of_obj_month))) && (value_checkBox_from_DB == false)) {

                    my_LinerLayout_Month_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
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

                    if (data_long == 0) { // если у задачи нет даты (дата = 01.01.1970 или data_long==0) то ничего в поле дата не пишем
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
                            data_long);

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
                    }

                    if ((data_long == 0) && (value_checkBox_from_DB == false)) { // если у задачи нет даты то ее тоже в поле "Потом"
                        my_LinerLayout_Late_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                    }


                    if (date_obj.before(current_date) && (value_checkBox_from_DB == false)) { // может сделать для просроченных отдельное поле???
                        my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        System.out.println("=======Date1 is before Date2=======");
                        System.out.println("=======add to layoyt TODAY=======");

                    }


                    if (date_obj.equals(current_date) && (data_long != 0) && (value_checkBox_from_DB == false)) {
                        my_LinerLayout_Today_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());

                        System.out.println("======Date1 is equal Date2=====");
                    }

                    if (date_obj.equals(current_date_plus_day) && (value_checkBox_from_DB == false)) {
                        my_LinerLayout_Tomorrow_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                        System.out.println("!!!====current_date_plus_day===!!!" + formatter.format(current_date_plus_day));

                    }

                    if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                            ((num_of_current_week.equals(num_of_obj_week))) && (value_checkBox_from_DB == false)) {

                        my_LinerLayout_Week_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
                    }

                    if ((date_obj.after(current_date)) && (!(date_obj.equals(current_date_plus_day))) &&
                            (!(num_of_current_week.equals(num_of_obj_week))) &&
                            ((num_of_current_month.equals(num_of_obj_month))) && (value_checkBox_from_DB == false)) {

                        my_LinerLayout_Month_01.addView(my_txtView_from_List_Two.get(j).getMy_linearLayout());
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


            }


        });
    }
//////////////////////////



    public void task_listener(TextView textView, TextView TextView_Data, int task_id, long value_data) {
        textView.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.hi_android, Toast.LENGTH_LONG).show();
                startEditTaskActivity(textView);

                Edit_Task_Activity eta= new Edit_Task_Activity();
                eta.string_text_from_task= (String) textView.getText();


                Date date_object = new Date();
                date_object.setTime(value_data);
                String data_for_edit = "";

                SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy"); // описание http://proglang.su/java/date-and-time
                data_for_edit = formatForDateNow.format(date_object);
                eta.string_text_from_data=data_for_edit;

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






}