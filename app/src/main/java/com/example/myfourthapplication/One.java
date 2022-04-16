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
import android.widget.RelativeLayout;
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
    private final RelativeLayout my_relativeLayout;
    private final ConstraintLayout my_constraintLayout;



    One(String name, int count, TextView textView, CheckBox checkBox,LinearLayout linearLayout,
        RelativeLayout relativeLayout, ConstraintLayout constraintLayout) {
        this.name = name;
        this.count = count;
        this.my_textView = textView;
        this.my_checkBox = checkBox;
        this.my_linearLayout = linearLayout;
        this.my_relativeLayout = relativeLayout;
        this.my_constraintLayout = constraintLayout;





// параметры для checkBox
        checkBox.setId(View.generateViewId());
        checkBox.setButtonTintList(ColorStateList.valueOf(Color.BLACK));  // цвет checkBox

        ConstraintLayout.LayoutParams layoutParams_checkBox = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            //    (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
      //  layoutParams_checkBox.width=0;
      //  layoutParams_checkBox.height=10;
     //   layoutParams_checkBox.horizontalWeight=9;
       // layoutParams_checkBox.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
      //  layoutParams_checkBox.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
       // layoutParams_checkBox.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;


     //   checkBox.setLayoutParams(layoutParams_checkBox);


// параметры для textView
        textView.setText("text from class");
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setTextColor(Color.BLACK);
       // textView.setTextAlignment(TextView.TEXT_ALIGNMENT_TEXT_START);
      //  textView.setTextAlignment(TextView.TEXT_ALIGNMENT_TEXT_END);
        textView.setTextSize(28);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
       // layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
       // layoutParams.width=0;
      //  layoutParams.height=80;
        layoutParams.setMarginStart(100);
        layoutParams.gravity = Gravity.RIGHT;



       // layoutParams.gravity = Gravity.CENTER;
       // layoutParams.horizontalWeight=10;
      //   layoutParams.rightToLeft = textView.getId();
       // layoutParams.leftToRight = checkBox.getId();
      //  layoutParams.rightToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        textView.setLayoutParams(layoutParams);
        checkBox.setLayoutParams(layoutParams);
       // layoutParams_checkBox.rightToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
      //  textView.setLayoutParams(layoutParams_checkBox);






// параметры для linearLayout



        LinearLayout.LayoutParams layoutParams_linearLayout = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
       // linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
       // linearLayout.setOrientation(LinearLayout.HORIZONTAL);



        linearLayout.setLayoutParams(layoutParams_linearLayout);






       // linearLayout.addView(checkBox);



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
        linearLayout.addView(checkBox);

       /*
        linearLayout.addView(textView, new LinearLayout.LayoutParams
                ( 2,LinearLayout.LayoutParams.MATCH_PARENT));
*/
// параметры для RelativeLayout
      /*
        RelativeLayout.LayoutParams layoutParams_relativeLayout = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        relativeLayout.setLayoutParams(layoutParams_relativeLayout);



       // layoutParams_relativeLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
        layoutParams_relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        textView.setLayoutParams(layoutParams_relativeLayout);

*/

      //  relativeLayout.addView(textView);

// параметры для ConstraintLayout
     /*   ConstraintLayout.LayoutParams layoutParams_ConstraintLayout = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT , ConstraintLayout.LayoutParams.MATCH_PARENT);

        layoutParams_ConstraintLayout.rightToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
      //  layoutParams_ConstraintLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
       // textView.setLayoutParams(layoutParams_ConstraintLayout);
        checkBox.setLayoutParams(layoutParams_ConstraintLayout);

       constraintLayout.addView(textView);
        constraintLayout.addView(checkBox);*/
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

    RelativeLayout getMy_relativeLayout() {
        return my_relativeLayout;
    }

    ConstraintLayout getMy_constraintLayout() {
        return my_constraintLayout;
    }
}
