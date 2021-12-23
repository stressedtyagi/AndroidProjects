package com.example.thecreator.eatit;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by The Creator on 12-Nov-17.
 */

public class soundPlayer {

    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int startSound;
    private static int gameOverSound;
    private static int specialSound;

    // Temp
    private AudioAttributes audioAttributes;
    final int SOUND_MAX = 2;

    public soundPlayer(Context context){

        /*
        // IF API Build is Below API 21 [Lollipop]
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            audioAttributes = new AudioAttributes().Builder().setUsage(AudioAttributes.USAGE_GAME).setContextType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool().Builder().setAudioAttributes(audioAttributes).setMaxStreams(SOUND_MAX).build();
        }else {
            //soundPool(int maxStreams, int streamType, int srcQuality)
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }*/

        //soundPool(int maxStreams, int streamType, int srcQuality)
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        hitSound = soundPool.load(context, R.raw.hit, 1);
        overSound = soundPool.load(context, R.raw.over, 1);
        startSound = soundPool.load(context, R.raw.start, 1);
        gameOverSound = soundPool.load(context, R.raw.gameover, 1);
        specialSound = soundPool.load(context, R.raw.special, 1);
    }

    public void playHitSound() {
        soundPool.play(hitSound,1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playOverSound() {
        soundPool.play(overSound,1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playStartSound() {
        soundPool.play(startSound,1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playGameOverSound() {
        soundPool.play(gameOverSound,1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playSpecialSound() {
        soundPool.play(specialSound,1.0f, 1.0f, 1, 0, 1.0f);
    }
}
