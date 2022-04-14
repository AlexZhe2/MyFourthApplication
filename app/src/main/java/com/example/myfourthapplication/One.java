package com.example.myfourthapplication;

import android.widget.TextView;

public class One {



        private final String name;
        private final int count;
        private final TextView my_textView;

   // TextView textView = new TextView(this);

        One(String name, int count,TextView textView) {
            this.name = name;
            this.count = count;
            this.my_textView = textView;


            textView.setText("from class");
        }

        String getName() {return name;}
        int getCount() {return count;}
    TextView getMy_textView() {return my_textView;}




}
