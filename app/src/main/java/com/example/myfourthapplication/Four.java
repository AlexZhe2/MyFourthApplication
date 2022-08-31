package com.example.myfourthapplication;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;


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


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

public class Four extends AppCompatActivity {

    private final TextView my_textView;
    private final LinearLayout my_linearLayout;

     Four(TextView textView,LinearLayout linearLayout) {
        this.my_textView = textView;
         this.my_linearLayout = linearLayout;


        LinearLayout.LayoutParams layoutParams_linearLayout_2 = new LinearLayout.LayoutParams
              //  (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        linearLayout.setLayoutParams(layoutParams_linearLayout_2);



        textView.setText("text from class");
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(24);

        LinearLayout.LayoutParams layoutParams_1 = new LinearLayout.LayoutParams
            //    (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 9);
                (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 9);

        textView.setLayoutParams(layoutParams_1);


/*

         ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                 (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
       //  layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
         layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        // layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
         textView.setLayoutParams(layoutParams);
*/



         linearLayout.addView(textView);   // добавление элемента

    }

    TextView getMy_textView() {
        return my_textView;
    }

    LinearLayout getMy_linearLayout() {
        return my_linearLayout;
    }

}
