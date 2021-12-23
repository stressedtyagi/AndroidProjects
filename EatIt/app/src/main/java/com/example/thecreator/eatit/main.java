package com.example.thecreator.eatit;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class main extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView black;
    private ImageView pink;

    //Size
    private int frameHeight;
    private int boxSize;
    private int screenWidth;
    private int screenHeight;

    //Positions
    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;

    // Score [10 points to get Orange, 50 to get Pink]
    private int score = 0;

    //Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private soundPlayer sound;

    // Status Check
    private boolean action_flag = false;
    private boolean start_flag = false;

    //Speed [TO Optimize for Different Devices]
    private int boxSpeed;
    private int orangeSpeed;
    private int blackSpeed;
    private int pinkSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new soundPlayer(this);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        black = (ImageView) findViewById(R.id.black);
        pink = (ImageView) findViewById(R.id.pink);

        //Get screen size
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenHeight = size.y;
        screenWidth = size.x;

        //Move entities out of screen
        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);

//         Random Values To Optimize speed in every Device
//         To Keep Speed as Follow
//         Speed_Box :: 21
//         Speed_orange :: 12
//         Speed_pink :: 20
//         Speed_Black :: 16

        boxSpeed = Math.round(screenHeight / 60F);
        orangeSpeed = Math.round(screenWidth / 60F);
        pinkSpeed = Math.round(screenWidth / 36F);
        blackSpeed = Math.round(screenWidth / 45F);

//        Log.v("Speed_Box : ", boxSpeed + "");
//        Log.v("Speed_orange : ", orangeSpeed + "");
//        Log.v("Speed_pink : ", pinkSpeed + "");
//        Log.v("Speed_Black : ", blackSpeed + "");


        scoreLabel.setText("Score : 0");
    }

    public void changePos(){

        hitCheck();

        //Orange
        orangeX -= orangeSpeed;
        if(orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);


        //Black
        blackX -= blackSpeed;
        if(blackX < 0) {
            blackX = screenWidth + 10;
            blackY = (int) Math.floor(Math.random() * (frameHeight - black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);

        //Pink
        pinkX -= pinkSpeed;
        if(pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (int) Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);


        //Move Box
        if(action_flag){
            //Touching the screen
            boxY -= boxSpeed;
        }else {
            //Releasing
            boxY += boxSpeed;
        }

        //Check box position
        if(boxY < 0)
            boxY = 0;
        if(boxY > frameHeight - boxSize)
            boxY = frameHeight - boxSize;
        box.setY(boxY);

        scoreLabel.setText("Score : " + score);
    }

    public void hitCheck() {
        // If player hit the center of the ball then it is a Hit.

        // Orange
        int orangeCenterX = orangeX + orange.getWidth() / 2;
        int orangeCenterY = orangeY + orange.getHeight() / 2;

        // 0 <= orangeCenterX <= boxWidth --> HIT
        // boxY <= orangeCenterY <= boxY + boxHeight --> HIT

        if(0 <= orangeCenterX && orangeCenterX <= boxSize &&
                boxY <= orangeCenterY && orangeCenterY <= boxY + boxSize){

            score += 10;
            orangeX = -10;
            sound.playHitSound();
        }


        // Pink
        int pinkCenterX = pinkX + pink.getWidth() / 2;
        int pinkCenterY = pinkY + pink.getHeight() / 2;

        if(0 <= pinkCenterX && pinkCenterX <= boxSize &&
                boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize){

            score += 50;
            pinkX = -10;
            sound.playSpecialSound();
        }

        // Black
        int blackCenterX = blackX + black.getWidth() / 2;
        int blackCenterY = blackY + black.getHeight() / 2;

        if(0 <= blackCenterX && blackCenterX <= boxSize &&
                boxY <= blackCenterY && blackCenterY <= boxY + boxSize){

            //Stop Timer
            timer.cancel();
            timer = null;

            sound.playOverSound();
            sound.playGameOverSound();

            //Show Result
            Intent intent = new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        if(start_flag){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                action_flag = true;
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                action_flag = false;
            }
        }else {
            start_flag = true;

            // Height taken here since UI has not been set on the screen in OnCreate()
            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            boxY = (int) box.getY();

            boxSize = box.getHeight(); // Box is square so height and width are same

            startLabel.setVisibility(View.GONE); // Tap To Start is Visible

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20); // Change position in every 20ms
        }

        return true;
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
