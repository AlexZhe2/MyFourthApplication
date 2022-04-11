package com.example.myfourthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton ImageButton_01;
    private ImageButton ImageButton_02;
    private ImageButton ImageButton_03;
    private ImageButton ImageButton_04;

    private LinearLayout my_LinerLayout_01;
    private View my_view_01;

    private EditText editText_01;
    private TextView TextView_01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton_01 = findViewById(R.id.ImageButton_xml_01);
        ImageButton_02 = findViewById(R.id.ImageButton_xml_02);
        ImageButton_03 = findViewById(R.id.ImageButton_xml_03);
        ImageButton_04 = findViewById(R.id.ImageButton_xml_04);

        editText_01 = findViewById(R.id.EditText_1);
        TextView_01 = findViewById(R.id.textView2);

        testFunction_01(ImageButton_01);
        testFunction_01(ImageButton_02);
        testFunction_01(ImageButton_03);
        testFunction_01(ImageButton_04);

        add_task_01(ImageButton_04);

       /* editText_01.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN &&
                        (i == KeyEvent.KEYCODE_ENTER))
                {
                    // сохраняем текст, введённый до нажатия Enter в переменную
                    String strCatName = editText_01.getText().toString();
                    TextView_01.setText(strCatName);
                    return true;
                }
                return false;
            }


        });*/

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
                /////


                findViewById(R.id.EditText_1).setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if(keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                (i == KeyEvent.KEYCODE_ENTER))
                        {
                            // сохраняем текст, введённый до нажатия Enter в переменную
                            EditText editText_03=findViewById(R.id.EditText_1);
                            String strCatName = editText_03.getText().toString();
                            TextView_01.setText(strCatName);


                            return true;
                        }
                        return false;
                    }


                });
                /////
             /*    EditText editText_022;
                editText_022 = findViewById(R.id.EditText_1);
                editText_022.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if(keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                (i == KeyEvent.KEYCODE_ENTER))
                        {
                            // сохраняем текст, введённый до нажатия Enter в переменную
                            String strCatName = editText_022.getText().toString();
                            TextView_01.setText(strCatName);
                            editText_022.setText(strCatName);
                            return true;
                        }
                        return false;
                    }


                });*/
                ////////


            }
        });
    }

    // метод вызывается из с помощью прописанного в xml файле "activity_main.xml" для
    // кнопки Button ("@+id/button3") атрибута android:onClick="startTaskActivity"
    public void startTaskActivity(View view) {
        Intent intent = new Intent(this, Task_Activity.class);
        startActivity(intent);
    }


    public boolean onKey_01(View v, int keyCode, KeyEvent event)
    {
        if(event.getAction() == KeyEvent.ACTION_DOWN &&
                (keyCode == KeyEvent.KEYCODE_ENTER))
        {
            // сохраняем текст, введённый до нажатия Enter в переменную
            String strCatName = editText_01.getText().toString();
            editText_01.setText(strCatName);
            return true;
        }
        return false;
    }
}