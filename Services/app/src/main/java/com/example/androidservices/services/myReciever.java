package com.example.androidservices.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by The Creator on 13-Sep-17.
 */

public class myReciever extends BroadcastReceiver {


     @Override
        public void onReceive(Context context, Intent intent){

         String status = "Off";
         String status1 = "Off";
         CharSequence intentData = intent.getCharSequenceExtra("message");

         if(intent.getAction() == "android.intent.action.AIRPLANE_MODE"){
             status1 = "On";
             Toast.makeText(context, "AirPlane Mode " + status1, Toast.LENGTH_LONG).show();

         }else if (intent.getAction() == "android.intent.action.CAMERA_BUTTON"){
             status = "On";
             Toast.makeText(context, "Camera" + status, Toast.LENGTH_LONG).show();
         }
         else {
             Toast.makeText(context, "Message: BroadCastServices Added :- " + intentData, Toast.LENGTH_LONG).show();
         }
        }
}


