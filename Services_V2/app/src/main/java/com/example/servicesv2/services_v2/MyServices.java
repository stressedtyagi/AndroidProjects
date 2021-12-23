package com.example.servicesv2.services_v2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by test on 20/09/17.
 */

public class MyServices extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId){
        Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG).show();
    }
}
