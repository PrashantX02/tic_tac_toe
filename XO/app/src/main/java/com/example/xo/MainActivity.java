package com.example.xo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.audiofx.PresetReverb;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    boolean backPressOrNot = false;
    boolean openNewActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!backPressOrNot){
                    openNewActivity = true;
                }else{
                    openNewActivity = false;
                }
                intentIt();
                finish();
            }
        },1700);



    }

    public void intentIt(){
        if(openNewActivity) {
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        backPressOrNot = true;
        super.onBackPressed();
    }


}