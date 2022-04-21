package com.example.myfourthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

            }

        });
    }

}