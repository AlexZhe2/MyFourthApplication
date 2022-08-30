package com.example.myfourthapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Two extends AppCompatActivity {

    private final TextView my_textView;
    private final TextView my_textView_DATA;
    private final CheckBox my_checkBox;
    private final LinearLayout my_linearLayout;
    private int my_task_id;


    Two(TextView textView, TextView textView_DATA, CheckBox checkBox, LinearLayout linearLayout, int task_id) {


        this.my_textView = textView;
        this.my_textView_DATA = textView_DATA;
        this.my_checkBox = checkBox;
        this.my_linearLayout = linearLayout;
        this.my_task_id = task_id;

// параметры для linearLayout
     //   linearLayout.setBackgroundColor(0xffe8eaf6);

        // создание и установка объекту который отвечает за настройки параметров для LinearLayout
        //нужных значений для характеристик которые потом будут переданы конкретному LinearLayout
        // в XML файле этозадается тегами
        LinearLayout.LayoutParams layoutParams_linearLayout_2 = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        linearLayout.setLayoutParams(layoutParams_linearLayout_2); // передача (установка) ранее
        // созданных настроек в конкретный linearLayout

//параметры для checkBox
        checkBox.setId(View.generateViewId());
        checkBox.setButtonTintList(ColorStateList.valueOf(Color.BLACK));  // цвет checkBox

        ConstraintLayout.LayoutParams layoutParams_checkBox = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//параметры для textView_DATA

        textView_DATA.setText("17-04-22");  // настройка для объекта textView
      //  textView_DATA.setBackgroundColor(Color.BLACK);
        textView_DATA.setTextColor(Color.BLACK);
        textView_DATA.setTextSize(10);
        // настройки для объекта который потом будет установлен именно в объект LinearLayout
        LinearLayout.LayoutParams layoutParams_DATA = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        textView.setLayoutParams(layoutParams_DATA);

//параметры для textView
        textView.setText("text from class");
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setTextColor(Color.BLACK);
       // textView.setTextSize(28);
        textView.setTextSize(24);

        LinearLayout.LayoutParams layoutParams_1 = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 9);
        //работает как надо только если указать "FILL_PARENT"

        checkBox.setLayoutParams(layoutParams_checkBox);
        textView.setLayoutParams(layoutParams_1);

        linearLayout.addView(checkBox);   //передача (установка) ранее созданных настроек
        linearLayout.addView(textView);
        linearLayout.addView(textView_DATA);


    }


    TextView getMy_textView() {
        return my_textView;
    }

    TextView getMy_textView_DATA() {
        return my_textView_DATA;
    }

    CheckBox getMy_checkBox() {
        return my_checkBox;
    }

    LinearLayout getMy_linearLayout() {
        return my_linearLayout;
    }

    int getMy_task_id() { return my_task_id;  }

    public void setMy_task_id(int id_from_object){
        my_task_id=id_from_object;
    }

}
