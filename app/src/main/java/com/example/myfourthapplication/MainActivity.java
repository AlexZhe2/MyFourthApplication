package com.example.myfourthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton ImageButton_01;
    private ImageButton ImageButton_02;
    private ImageButton ImageButton_03;
    private ImageButton ImageButton_04;

    private LinearLayout my_LinerLayout_01;
    private View my_view_01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton_01 = findViewById(R.id.ImageButton_xml_01);
        ImageButton_02 = findViewById(R.id.ImageButton_xml_02);
        ImageButton_03 = findViewById(R.id.ImageButton_xml_03);
        ImageButton_04 = findViewById(R.id.ImageButton_xml_04);

        testFunction_01(ImageButton_01);
        testFunction_01(ImageButton_02);
        testFunction_01(ImageButton_03);
        testFunction_01(ImageButton_04);

        add_task_01(ImageButton_04);
    }

    public void testFunction_01(ImageButton value) { // показывает всплывающее сообщение
        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.hi_android, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void add_task_01(ImageButton value) {  // добавление новой задачи
        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);

                LayoutInflater inflater = getLayoutInflater();
                my_view_01 = inflater.inflate(R.layout.my_field_for_task, null);
                my_LinerLayout_01.addView(my_view_01);
            }
        });
    }

}