package com.example.audiosandbox;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView playPause;
    SeekBar seekBar;
    AudioManager audioManager;
    ImageView pauseIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sample);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(maxVolume);
        seekBar.setMin(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playPause = findViewById(R.id.playPause);
        pauseIcon = findViewById(R.id.pauseIcon);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    pause();
                    playPause.setAlpha(1.0f);
                    pauseIcon.setAlpha(0.0f);
                } else {
                    play();
                    playPause.setAlpha(0.0f);
                    pauseIcon.setAlpha(1.0f);
                }
            }
        });

    }


    public void play() {
        mediaPlayer.start();
    }

    public void pause(){
        mediaPlayer.pause();
    }

}