package com.example.thecreator.eatit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class result extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView highScoreLabel;

    // Initialize Classes
    private soundPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if(score > highScore){
            highScoreLabel.setText("High Score : " + score);

            // Save High Score
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }else {
            highScoreLabel.setText("High Score : " + highScore);
        }

        sound = new soundPlayer(this);
    }


    public void tryAgain(View view) {
        sound.playStartSound();
        startActivity(new Intent(getApplicationContext(), start.class));
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
