package com.madhouseapp.kidslearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private ImageView madhouseLogo;
    private TextView presents;
    private TextView kids, learning, app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        madhouseLogo = (ImageView) findViewById(R.id.splash_logo);
        presents = (TextView) findViewById(R.id.text_presents);
        kids = (TextView) findViewById(R.id.text_kids);
        learning = (TextView) findViewById(R.id.text_learning);
        app = (TextView) findViewById(R.id.text_app);

        Timer timer = new Timer();
        Timer kidsT = new Timer();
        final Timer learningT = new Timer();
        Timer appT = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                fadeIn.reset();
                madhouseLogo.clearAnimation();
                madhouseLogo.startAnimation(fadeIn);
                presents.clearAnimation();
                presents.startAnimation(fadeIn);
            }
        }, 0);

        kidsT.schedule(new TimerTask() {
            @Override
            public void run() {
                SplashActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
                        zoomIn.reset();
                        kids.clearAnimation();
                        kids.startAnimation(zoomIn);
                    }
                });
            }
        }, 500);

        learningT.schedule(new TimerTask() {
            @Override
            public void run() {
                SplashActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
                        zoomIn.reset();
                        learning.clearAnimation();
                        learning.startAnimation(zoomIn);
                    }
                });
            }
        }, 1000);

        appT.schedule(new TimerTask() {
            @Override
            public void run() {
                SplashActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
                        zoomIn.reset();
                        app.clearAnimation();
                        app.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
                        app.clearAnimation();
                        app.startAnimation(zoomIn);
                    }
                });
            }
        }, 1500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                finish();
            }
        }, 2000);
    }
}
