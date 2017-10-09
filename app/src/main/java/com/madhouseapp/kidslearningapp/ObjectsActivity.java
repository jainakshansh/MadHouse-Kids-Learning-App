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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.madhouseapp.kidslearningapp.Adapters.ImageAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;
import com.madhouseapp.kidslearningapp.Object.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class ObjectsActivity extends AppCompatActivity {

    private RecyclerView objectRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter adapter;

    private CenterZoomLayoutManager centerZoomLayoutManager;

    private Button previous, play, next;
    private int counter = 0;

    private MediaPlayer mediaPlayer;
    private int[] sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_objects);

        sounds = new int[]{R.raw.air_conditioner, R.raw.briefcase, R.raw.bucket, R.raw.clock, R.raw.cricket_bat, R.raw.cricket_ball,
                R.raw.chair, R.raw.pencil, R.raw.fan, R.raw.ladder, R.raw.laptop, R.raw.pen, R.raw.scissor, R.raw.smartphone,
                R.raw.toothbrush, R.raw.uchiwa};

        imageItemList = new ArrayList<>();
        initList();
        adapter = new ImageAdapter(this, imageItemList);

        objectRecycler = (RecyclerView) findViewById(R.id.recycler_objects);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        objectRecycler.setLayoutManager(centerZoomLayoutManager);
        objectRecycler.setItemAnimator(new DefaultItemAnimator());
        objectRecycler.setAdapter(adapter);

        previous = (Button) findViewById(R.id.previous_objects);
        play = (Button) findViewById(R.id.play_objects);
        next = (Button) findViewById(R.id.next_objects);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (centerZoomLayoutManager.findFirstVisibleItemPosition() != imageItemList.size() - 2) {
                    counter = centerZoomLayoutManager.findLastVisibleItemPosition() - 1;
                } else {
                    counter = centerZoomLayoutManager.findFirstVisibleItemPosition() + 1;

                }
                counter--;
                if (counter < 0) {
                    counter = imageItemList.size() - 1;
                    objectRecycler.scrollToPosition(counter);
                } else {
                    objectRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                counter++;
                if (counter > imageItemList.size() - 1) {
                    counter = 0;
                    objectRecycler.scrollToPosition(counter);
                } else {
                    objectRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        objectRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(objectRecycler);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[counter]);
                mediaPlayer.start();

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                play.clearAnimation();
                play.startAnimation(animation);
            }
        });
    }

    private void initList() {
        imageItemList.add(new ImageItem("Air Conditioner", R.drawable.air_conditioner));
        imageItemList.add(new ImageItem("Briefcase", R.drawable.briefcase));
        imageItemList.add(new ImageItem("Bucket", R.drawable.bucket));
        imageItemList.add(new ImageItem("Clock", R.drawable.clock));
        imageItemList.add(new ImageItem("Cricket Bat", R.drawable.cricket_bat));
        imageItemList.add(new ImageItem("Cricket Ball", R.drawable.cricket));
        imageItemList.add(new ImageItem("Chair", R.drawable.desk_chair));
        imageItemList.add(new ImageItem("Pencil", R.drawable.edit));
        imageItemList.add(new ImageItem("Fan", R.drawable.fan));
        imageItemList.add(new ImageItem("Ladder", R.drawable.ladder));
        imageItemList.add(new ImageItem("Laptop", R.drawable.laptop));
        imageItemList.add(new ImageItem("Pen", R.drawable.pen));
        imageItemList.add(new ImageItem("Scissors", R.drawable.scissors));
        imageItemList.add(new ImageItem("SmartPhone", R.drawable.smartphone));
        imageItemList.add(new ImageItem("Toothbrush", R.drawable.toothbrush));
        imageItemList.add(new ImageItem("Uchiwa", R.drawable.uchiwa));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
