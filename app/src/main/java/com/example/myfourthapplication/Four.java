package com.example.myfourthapplication;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class Four extends AppCompatActivity {

    private final TextView my_textView;
    private final TextView my_textView_02;
    private  boolean have_notification;
    private  boolean have_subtask=false;
    private final ImageView my_ImageView_01;
    private final ImageView my_ImageView_02;
    private final LinearLayout my_linearLayout;

     Four(TextView textView, TextView my_textView_02, boolean have_notification, boolean have_subtask,
          ImageView my_ImageView_01, ImageView my_imageView_02, LinearLayout linearLayout) {
        this.my_textView = textView;
         this.my_textView_02 = my_textView_02;
         this.have_notification = have_notification;
         this.have_subtask = have_subtask;
         this.my_ImageView_01 = my_ImageView_01;
         this.my_ImageView_02 = my_imageView_02;
         this.my_linearLayout = linearLayout;


        LinearLayout.LayoutParams layoutParams_linearLayout_2 = new LinearLayout.LayoutParams
              //  (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        linearLayout.setLayoutParams(layoutParams_linearLayout_2);



        textView.setText("  ");
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(12);

        LinearLayout.LayoutParams layoutParams_1 = new LinearLayout.LayoutParams
            //    (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 9);
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        textView.setLayoutParams(layoutParams_1);


         my_textView_02.setText("  ");
         my_textView_02.setBackgroundColor(Color.parseColor("#eeeeee"));
         my_textView_02.setTextColor(Color.BLACK);
         my_textView_02.setTextSize(12);

         my_textView_02.setLayoutParams(layoutParams_1);




         LinearLayout.LayoutParams layoutParams_2 = new LinearLayout.LayoutParams
                 (40,40);

         my_ImageView_01.setLayoutParams(layoutParams_2);
         my_ImageView_01.setImageResource(R.drawable.ic_image_06);


         my_imageView_02.setLayoutParams(layoutParams_2);
         my_imageView_02.setImageResource(R.drawable.ic_image_01);
/*

         ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                 (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
       //  layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
         layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        // layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
         textView.setLayoutParams(layoutParams);
*/



       //  linearLayout.addView(textView);   // добавление элемента

    }

    TextView getMy_textView() {
        return my_textView;
    }

    TextView getMy_textView_02() {
        return my_textView_02;
    }

    boolean getMy_have_notification() {return have_notification; }

    public void setMy_have_notification(boolean have_notif){
        have_notification=have_notif;
    }

    public void setMy_have_subtask(boolean have_sub){
        have_subtask=have_sub;
    }

    boolean getMy_have_subtask() {return have_subtask; }

    ImageView getMy_ImageView_01() {return my_ImageView_01; }

    ImageView getMy_ImageView_02() {return my_ImageView_02; }


    LinearLayout getMy_linearLayout() {
        return my_linearLayout;
    }

}
