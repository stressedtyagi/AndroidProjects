package com.example.thecreator.eatit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

public class start extends AppCompatActivity {

    // Initialize Classes
    private soundPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sound = new soundPlayer(this);
    }

    public void startGame(View view){
        sound.playStartSound();
        startActivity(new Intent(getApplicationContext(), main.class));
    }

    // Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch ((event.getKeyCode())){
                case KeyEvent.KEYCODE_BACK :
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
