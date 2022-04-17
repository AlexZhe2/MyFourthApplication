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

    private final String name;
    private final int count;
    private final TextView my_textView;
    private final TextView my_textView_DATA;
    private final CheckBox my_checkBox;
    private final LinearLayout my_linearLayout;
    private final RelativeLayout my_relativeLayout;
    private final ConstraintLayout my_constraintLayout;



    Two(String name, int count, TextView textView,TextView textView_DATA, CheckBox checkBox, LinearLayout linearLayout,
        RelativeLayout relativeLayout, ConstraintLayout constraintLayout) {
        this.name = name;
        this.count = count;
        this.my_textView = textView;
        this.my_textView_DATA = textView_DATA;
        this.my_checkBox = checkBox;
        this.my_linearLayout = linearLayout;
        this.my_relativeLayout = relativeLayout;
        this.my_constraintLayout = constraintLayout;


/////////

// параметры для checkBox
        checkBox.setId(View.generateViewId());
        checkBox.setButtonTintList(ColorStateList.valueOf(Color.BLACK));  // цвет checkBox

        ConstraintLayout.LayoutParams layoutParams_checkBox = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
/*
// параметры для textView
        textView.setText("text from class");
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setTextColor(Color.BLACK);

        textView.setTextSize(28);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

        layoutParams.setMarginStart(100);
        layoutParams.gravity = Gravity.RIGHT;



        textView.setLayoutParams(layoutParams);
        checkBox.setLayoutParams(layoutParams);

// параметры для linearLayout


        LinearLayout.LayoutParams layoutParams_linearLayout = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        linearLayout.setLayoutParams(layoutParams_linearLayout);



        //linearLayout.addView(textView);
        linearLayout.addView(textView);
        linearLayout.addView(checkBox);
*/


// параметры для linearLayout
        linearLayout.setBackgroundColor(0xffe8eaf6);

        LinearLayout.LayoutParams layoutParams_linearLayout_2 = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
         linearLayout.setOrientation(LinearLayout.HORIZONTAL);
       // linearLayout.setOrientation(LinearLayout.VERTICAL);


        linearLayout.setLayoutParams(layoutParams_linearLayout_2);

//  параметры для textView_DATA

        textView_DATA.setText("17-04-22");
        textView_DATA.setBackgroundColor(Color.RED);
        textView_DATA.setTextColor(Color.BLACK);
        textView_DATA.setTextSize(10);
        LinearLayout.LayoutParams layoutParams_DATA = new LinearLayout.LayoutParams
           //     (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
             (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT,1);
        textView.setLayoutParams(layoutParams_DATA);



//        параметры для textView
        textView.setText("text from class");
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setTextColor(Color.BLACK);

        textView.setTextSize(28);

        ////


       LinearLayout.LayoutParams layoutParams_1 = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,9);
           //     (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

       //  linearLayout.setOrientation(LinearLayout.HORIZONTAL);
       // linearLayout.setOrientation(LinearLayout.VERTICAL);


   //     layoutParams.setMarginStart(100);
        layoutParams_1.gravity = Gravity.RIGHT;


        checkBox.setLayoutParams(layoutParams_checkBox);
        textView.setLayoutParams(layoutParams_1);

        linearLayout.addView(checkBox);
        linearLayout.addView(textView);
        linearLayout.addView(textView_DATA);


      //  textView = findViewById(R.id.textView2);

////////
    }

    String getName() {
        return name;
    }

    int getCount() {
        return count;
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

    RelativeLayout getMy_relativeLayout() {
        return my_relativeLayout;
    }

    ConstraintLayout getMy_constraintLayout() {
        return my_constraintLayout;
    }
}
