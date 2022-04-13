package com.panshuljindal.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekbar;
    Button button;
    TextView textView;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        counterIsActive = false;
        textView.setText("00:30");
        seekbar.setProgress(30);
        seekbar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go!");
    }
    public void updateTimer(int secondsLeft){
        int min =secondsLeft/60;
        int seconds = secondsLeft-(min*60);
        String secondString;
        secondString = Integer.toString(seconds);
        if(seconds<=9){
            secondString="0"+secondString;
        }
        textView.setText(Integer.toString(min) + ":" + secondString);;
    }
    public void buttonPressed(View view){
        if(counterIsActive){
            resetTimer();
        }
        else {
            counterIsActive = true;
            seekbar.setEnabled(false);
            button.setText("Stop!");
            countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }
                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();


    }}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        seekbar = findViewById(R.id.seekBar);
        button = findViewById(R.id.button);
        seekbar.setMax(600);
        seekbar.setProgress(30);
        textView.setText("00:30");
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
