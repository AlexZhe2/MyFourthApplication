package com.example.myfourthapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
    private View my_view_01;

    private EditText editText_01;
    private TextView TextView_01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);

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

        //  add_task_01(ImageButton_04);
        // add_task_02(ImageButton_04);
        add_task_03(ImageButton_04);

        ////////


        //попробовать ложить в массив (Linked List) "class MethodResult" + прописать создание через него
        // элементов для Activity
        class MethodResult {
            private final String name;
            private final int count;


            MethodResult(String name, int count) {
                this.name = name;
                this.count = count;

            }

            String getName() {
                return name;
            }

            int getCount() {
                return count;
            }

        }

        MethodResult mr = new MethodResult("a1", 2);
        //////////

        //  One o1 = new One("a1",5);


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
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);

                LayoutInflater inflater = getLayoutInflater();
                my_view_01 = inflater.inflate(R.layout.my_field_for_task, null);
                my_LinerLayout_01.addView(my_view_01);

                ////////


                EditText editText_03 = findViewById(R.id.EditText_1);
                EditText editText_032 = findViewById(R.id.EditText_2);

                //int a3 = editText_03.generateViewId();
                // editText_03.setId(a3);

                //  editText_03.setId(0x7f040000);
                //int a1=editText_03.getId();
                //  int a1=editText_03.getImeActionId();

                //  editText_03.setTag(1);
                //  editText_03.generateViewId();


                // String idString = editText_03.getResources().getResourceEntryName(editText_03.getId()); // work
                //editText_03.setHint(editText_03.getId());
                // editText_03.setText(idString);
                //    editText_03.setText(a3);


                //  my_view_01.findViewById(R.id.EditText_1).setId(123);

                findViewById(R.id.EditText_1).setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                (i == KeyEvent.KEYCODE_ENTER)) {
                            // сохраняем текст, введённый до нажатия Enter в переменную
                            //   EditText editText_03=findViewById(R.id.EditText_1);
                            //  EditText editText_032=findViewById(R.id.EditText_2);


                            String strCatName = editText_03.getText().toString();
                            // TextView_01.setText(strCatName);
                            editText_032.setText(strCatName);


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


    public boolean onKey_01(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            // сохраняем текст, введённый до нажатия Enter в переменную
            String strCatName = editText_01.getText().toString();
            editText_01.setText(strCatName);
            return true;
        }
        return false;
    }


    /*  public void a1(){
          findViewById(R.id.EditText_1).setOnKeyListener(new View.OnKeyListener() {
              @Override
              public boolean onKey(View view, int i, KeyEvent keyEvent) {
                  if(keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                          (i == KeyEvent.KEYCODE_ENTER))
                  {
                      Toast.makeText(MainActivity.this, R.string.hi_android, Toast.LENGTH_LONG).show();
                      // сохраняем текст, введённый до нажатия Enter в переменную
                     // EditText editText_03=findViewById(R.id.EditText_1);
                    //  EditText editText_032=findViewById(R.id.EditText_2);
                     // String strCatName = editText_03.getText().toString();
                      // TextView_01.setText(strCatName);
                   //   editText_032.setText(strCatName);


                      return true;
                  }
                  return false;
              }


          });
          }
          */
    public void a1() {
        EditText editText_03 = findViewById(R.id.EditText_1);

        String idString = editText_03.getResources().getResourceEntryName(editText_03.getId()); // work
        //editText_03.setHint(editText_03.getId());
        editText_03.setText(idString);
    }

    ArrayList<TextView> my_txtView_from_List = new ArrayList<TextView>();
    ArrayList<One> my_txtView_from_List_One = new ArrayList<One>();

    //  String [] birthdays = new String[10]; //массив строк Java
    //  TextView [] my_txtView_from_List_02 = new TextView[10];

    int i = 0;

    public void ttt() {
        TextView textView = new TextView(this);
        my_txtView_from_List.add(textView);

        // my_txtView_from_List.add(new TextView(this)); // тоже работает
    }

    public void tt2() {
        One my_one_01 = new One("a1", 1, new TextView(this), new CheckBox(this),
                new LinearLayout(this));
        my_txtView_from_List_One.add(my_one_01);
    }


    public void add_task_02(ImageButton value) {  // добавление новой задачи
        value.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                //   my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);

                // LayoutInflater inflater = getLayoutInflater();
                //  my_view_01 = inflater.inflate(R.layout.my_field_for_task, null);
                //  my_LinerLayout_01.addView(my_view_01);

                ttt();

                //    my_txtView_from_List.get(i).setBackgroundColor(0xffe8eaf6);

                //     ConstraintLayout constraintLayout = new ConstraintLayout(this);
                //   TextView textView = new TextView(this);
                // установка фонового цвета
                //  textView.setBackgroundColor(0xffe8eaf6);
                //   my_txtView_from_List.get(i).setBackgroundColor(0xffe8eaf6);
                // установка цвета текста
                //  textView.setTextColor(0xff5c6bc0);
                // my_txtView_from_List.get(i).setTextColor(0xff5c6bc0);
                // делаем все буквы заглавными
                //  textView.setAllCaps(true);
                //   my_txtView_from_List.get(i).setAllCaps(true);
                // устанавливаем вравнивание текста по центру
                //  textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                my_txtView_from_List.get(i).setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                // устанавливаем текста
                //  textView.setText("Hello Android!");
                my_txtView_from_List.get(i).setText("Hello Android!");
                if (i == 3) {
                    my_txtView_from_List.get(i).setText("Hello-Hello Android!!!");
                }


                // установка шрифта
                //    textView.setTypeface(Typeface.create("casual", Typeface.NORMAL));
                // устанавливаем высоту текста
                //  textView.setTextSize(26);
                my_txtView_from_List.get(i).setTextSize(26);


                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                        (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;


                //    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                //           (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //layoutParams.leftToLeft = LinearLayout.LayoutParams.PARENT_ID;
                //layoutParams.topToTop = LinearLayout.LayoutParams.PARENT_ID;


                //   textView.setLayoutParams(layoutParams);


                my_txtView_from_List.get(i).setLayoutParams(layoutParams);


                //  my_LinerLayout_01.addView(textView);
                //  my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);

                my_LinerLayout_01.addView(my_txtView_from_List.get(i));

                //   setContentView(my_LinerLayout_01);


                i++;

            }


        });
    }

    public void add_task_03(ImageButton value) {  // добавление новой задачи
        value.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                //   my_LinerLayout_01 = findViewById(R.id.my_LinerLayout_xml_01);

                // LayoutInflater inflater = getLayoutInflater();
                //  my_view_01 = inflater.inflate(R.layout.my_field_for_task, null);
                //  my_LinerLayout_01.addView(my_view_01);

                tt2();

                //    my_txtView_from_List.get(i).setBackgroundColor(0xffe8eaf6);

                //     ConstraintLayout constraintLayout = new ConstraintLayout(this);
                //   TextView textView = new TextView(this);
                // установка фонового цвета
                //  textView.setBackgroundColor(0xffe8eaf6);
                //  my_txtView_from_List.get(i).setBackgroundColor(0xffe8eaf6);

                //  my_txtView_from_List_One.get(i).getMy_textView().setBackgroundColor(0xffe8eaf6);
                // установка цвета текста
                //  textView.setTextColor(0xff5c6bc0);
                // my_txtView_from_List.get(i).setTextColor(0xff5c6bc0);

                // my_txtView_from_List_One.get(i).getMy_textView().setTextColor(0xff5c6bc0);
                // делаем все буквы заглавными
                //  textView.setAllCaps(true);
                // my_txtView_from_List.get(i).setAllCaps(true);
                //  my_txtView_from_List_One.get(i).getMy_textView().setAllCaps(true);
                // устанавливаем вравнивание текста по центру
                //  textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                //  my_txtView_from_List.get(i).setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                //   my_txtView_from_List_One.get(i).getMy_textView().setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                // устанавливаем текста
                //  textView.setText("Hello Android!");
                //  my_txtView_from_List.get(i).setText("Hello Android!");
                //  my_txtView_from_List_One.get(i).getMy_textView().setText("Hello Android!");
                if (i == 3) {
                    //     my_txtView_from_List.get(i).setText("Hello-Hello Android!!!");
                    my_txtView_from_List_One.get(i).getMy_textView().setText("Hello-Hello Android!!!");
                }


                // устанавливаем высоту текста
                //  textView.setTextSize(26);
                //   my_txtView_from_List.get(i).setTextSize(26);
                //  my_txtView_from_List_One.get(i).getMy_textView().setTextSize(26);

/*


                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                        (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;


                //    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                //           (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //layoutParams.leftToLeft = LinearLayout.LayoutParams.PARENT_ID;
                //layoutParams.topToTop = LinearLayout.LayoutParams.PARENT_ID;





             //   my_txtView_from_List.get(i).setLayoutParams(layoutParams);
                my_txtView_from_List_One.get(i).getMy_textView().setLayoutParams(layoutParams);


*/


                //   my_LinerLayout_01.addView(my_txtView_from_List.get(i));
              /*
                my_LinerLayout_01.addView(my_txtView_from_List_One.get(i).getMy_checkBox());
                my_LinerLayout_01.addView(my_txtView_from_List_One.get(i).getMy_textView());
                */
                my_LinerLayout_01.addView(my_txtView_from_List_One.get(i).getMy_linearLayout());


                //   setContentView(my_LinerLayout_01);


                i++;

            }


        });
    }


}