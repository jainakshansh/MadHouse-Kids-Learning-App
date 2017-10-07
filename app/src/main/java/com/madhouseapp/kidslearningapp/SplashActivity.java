package com.madhouseapp.kidslearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private ImageView madhouseLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        madhouseLogo = (ImageView) findViewById(R.id.splash_logo);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                fadeIn.reset();
                madhouseLogo.clearAnimation();
                madhouseLogo.startAnimation(fadeIn);
            }
        }, 0);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
                translate.reset();
                madhouseLogo.clearAnimation();
                madhouseLogo.startAnimation(translate);
            }
        }, 0);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                finish();
            }
        }, 2000);
    }
}
