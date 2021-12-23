package com.example.test.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    Integer number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void ac(View view){
        TextView textView = (TextView) findViewById(R.id.display);
        number = 0;
        textView.setText(number.toString());
    }

   protected void one(View view){
       TextView textView = (TextView) findViewById(R.id.display);
       number = 1;
        textView.setText(number.toString());
    }

    protected void two(View view){
        TextView textView = (TextView) findViewById(R.id.display);
        number = 2;
        textView.setText(number.toString());
    }

}
