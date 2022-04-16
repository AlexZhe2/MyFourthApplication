package com.example.myfourthapplication;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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
      //  textView.setTextAlignment(TextView.TEXT_ALIGNMENT_TEXT_END);
        textView.setTextSize(28);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
       // layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
       // layoutParams.width=0;
      //  layoutParams.height=80;
        layoutParams.setMarginStart(50);
        layoutParams.gravity = Gravity.RIGHT;
       // layoutParams.gravity = Gravity.CENTER;
       // layoutParams.horizontalWeight=10;
      //   layoutParams.rightToLeft = textView.getId();
       // layoutParams.leftToRight = checkBox.getId();
      //  layoutParams.rightToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        textView.setLayoutParams(layoutParams);

// параметры для linearLayout



        LinearLayout.LayoutParams layoutParams_linearLayout = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);



        linearLayout.setLayoutParams(layoutParams_linearLayout);





      //  linearLayout.addView(checkBox);



      //  linearLayout.addView(textView, layoutParams);
      //  linearLayout.addView(textView);


//
//
//        LinearLayout.LayoutParams layoutParams_linearLayout = new LinearLayout.LayoutParams
//                (80, LinearLayout.LayoutParams.WRAP_CONTENT);
//


     //   linearLayout.setOrientation(LinearLayout.HORIZONTAL);

       // linearLayout.setLayoutParams(layoutParams_linearLayout);
        //linearLayout.addView(textView);
        linearLayout.addView(textView);
       /*
        linearLayout.addView(textView, new LinearLayout.LayoutParams
                ( 2,LinearLayout.LayoutParams.MATCH_PARENT));
*/

        ConstraintSet constraintSet = new ConstraintSet();
       // constraintSet.clone(context, R.id.my_LinerLayout_xml_01);







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
