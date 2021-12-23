package com.example.servicesv2.services_v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by test on 20/09/17.
 */

public class MyBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context,Intent intent){
        String string = intent.getAction();
        Toast.makeText(context,"BoadCast " + string + " Started",Toast.LENGTH_LONG).show();
    }
}
