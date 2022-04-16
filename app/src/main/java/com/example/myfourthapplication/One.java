package com.example.myfourthapplication;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class One extends AppCompatActivity {

    private final String name;
    private final int count;
    private final TextView my_textView;
    private final CheckBox my_checkBox;
    private final LinearLayout my_linearLayout;



    One(String name, int count, TextView textView, CheckBox checkBox,LinearLayout linearLayout) {
        this.name = name;
        this.count = count;
        this.my_textView = textView;
        this.my_checkBox = checkBox;
        this.my_linearLayout = linearLayout;





// параметры для checkBox
        checkBox.setId(View.generateViewId());
        checkBox.setButtonTintList(ColorStateList.valueOf(Color.BLACK));  // цвет checkBox

        ConstraintLayout.LayoutParams layoutParams_checkBox = new ConstraintLayout.LayoutParams
           //     (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
      //  layoutParams_checkBox.width=0;
      //  layoutParams_checkBox.height=10;
        layoutParams_checkBox.horizontalWeight=2;
       // layoutParams_checkBox.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams_checkBox.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
       // layoutParams_checkBox.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;


        checkBox.setLayoutParams(layoutParams_checkBox);


// параметры для textView
        textView.setText("text from class");
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setTextColor(Color.BLACK);
       // textView.setTextAlignment(TextView.TEXT_ALIGNMENT_TEXT_START);
        textView.setTextSize(28);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        //layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
       // layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
       // layoutParams.width=0;
      //  layoutParams.height=80;
        layoutParams.horizontalWeight=10;
      //   layoutParams.rightToLeft = textView.getId();
       // layoutParams.leftToRight = checkBox.getId();
        layoutParams.rightToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        textView.setLayoutParams(layoutParams);

// параметры для linearLayout
        ConstraintLayout.LayoutParams layoutParams_linearLayout = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(layoutParams_linearLayout);
      //  linearLayout.addView(checkBox);
        linearLayout.addView(textView);
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

    CheckBox getMy_checkBox() {
        return my_checkBox;
    }

    LinearLayout getMy_linearLayout() {
        return my_linearLayout;
    }
}
