package com.example.myfourthapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;

public class Three extends AppCompatActivity {

    private final EditText my_EditText;
    private final TextView my_EditText_DATA;
    private final CheckBox my_checkBox;
    private final LinearLayout my_linearLayout;
    private int my_task_id;


    Three(EditText textView, TextView textView_DATA, CheckBox checkBox, LinearLayout linearLayout, int task_id) {

        this.my_EditText = textView;
        this.my_EditText_DATA = textView_DATA;
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

        textView_DATA.setText("X     ");  // настройка для объекта textView
      //  textView_DATA.setBackgroundColor(Color.BLACK);
        textView_DATA.setTextColor(Color.BLACK);
        textView_DATA.setTextSize(14);
      //  textView_DATA.setTypeface(Typeface.DEFAULT_BOLD);
        textView_DATA.setTypeface(null, Typeface.BOLD);
        // настройки для объекта который потом будет установлен именно в объект LinearLayout
        LinearLayout.LayoutParams layoutParams_DATA = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        textView.setLayoutParams(layoutParams_DATA);

//параметры для textView
        textView.setText("text from class");
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setTextColor(Color.BLACK);
       // textView.setTextSize(28);
        textView.setTextSize(15);
        textView.setPadding(15,15,15,15);


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
        return my_EditText;
    }

    TextView getMy_textView_DATA() {
        return my_EditText_DATA;
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


    public void instance_callAlarmManager2(Calendar calendar, Edit_Task_Activity eta_for_alarm2) {
        Intent my_intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        // PendingIntent pendingIntent =    PendingIntent.getBroadcast(Edit_Task_Activity.this,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent =    PendingIntent.getBroadcast(eta_for_alarm2,0, my_intent,  PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        System.out.println("==check instance_callAlarmManager2==");

    }


}
