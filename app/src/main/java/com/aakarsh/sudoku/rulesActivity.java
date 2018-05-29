package com.aakarsh.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;



public class rulesActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView rule1=(TextView)findViewById(R.id.rule1);
        TextView rule2=(TextView)findViewById(R.id.rule2);
        TextView rule3=(TextView)findViewById(R.id.rule3);

        rule1.setText("easy would generate 35 numbers ");
        rule2.setText("medium would generate 30 numbers ");
        rule3.setText("hard would generate 25 numbers ");

    }
}