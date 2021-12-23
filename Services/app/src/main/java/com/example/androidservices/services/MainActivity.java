package com.example.androidservices.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void broadcastCustomIntent(View view){

        textView = (TextView) findViewById(R.id.service);
        Intent intent = new Intent();
        // add data to the Intent
        intent.putExtra("message",(CharSequence)textView.getText().toString());
        intent.setAction("A_CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    public void click(){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}

