package com.madhouseapp.kidslearningapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.madhouseapp.kidslearningapp.Adapters.ColorsAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;

public class ColorsActivity extends AppCompatActivity {
    private RecyclerView colorRecycler;
    private ColorsAdapter adapter;
    private CenterZoomLayoutManager centerZoomLayoutManager;

    private Button previous, play, next;
    private int counter = 0;
    private int[] sounds;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_colors);

        sounds = new int[]{R.raw.red, R.raw.pink, R.raw.purple, R.raw.indigo, R.raw.blue, R.raw.sky_blue, R.raw.cyan, R.raw.teal,
                R.raw.green, R.raw.lime, R.raw.yellow, R.raw.amber, R.raw.orange, R.raw.brown, R.raw.grey, R.raw.black, R.raw.white};

        final int[] colors = getApplicationContext().getResources().getIntArray(R.array.colors);
        adapter = new ColorsAdapter(getApplicationContext());
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        colorRecycler = (RecyclerView) findViewById(R.id.recycler_colors);
        previous = (Button) findViewById(R.id.previous_colors);
        play = (Button) findViewById(R.id.play_colors);
        next = (Button) findViewById(R.id.next_colors);

        colorRecycler.setLayoutManager(centerZoomLayoutManager);
        colorRecycler.setItemAnimator(new DefaultItemAnimator());
        colorRecycler.setAdapter(adapter);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (centerZoomLayoutManager.findFirstVisibleItemPosition() != colors.length - 2) {
                    counter = centerZoomLayoutManager.findLastVisibleItemPosition() - 1;
                } else {
                    counter = centerZoomLayoutManager.findFirstVisibleItemPosition() + 1;

                }
                counter--;
                if (counter < 0) {
                    counter = colors.length - 1;
                    colorRecycler.scrollToPosition(counter);
                } else {
                    colorRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                counter++;
                if (counter > colors.length - 1) {
                    counter = 0;
                    colorRecycler.scrollToPosition(counter);
                } else {
                    colorRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        colorRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(colorRecycler);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[counter]);
                mediaPlayer.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
