package com.example.servicesv2.services_v2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Start(View view){
        intent = new Intent(this,MyServices.class);
        startService(intent);
    }

    public void Stop(View view){
        intent = new Intent(this,MyServices.class);
        stopService(intent);
    }

    public void StartBroad(View view){
        sendBroadcast(new Intent(this,MyBroadcast.class).setAction("Custom"));
    }

}

