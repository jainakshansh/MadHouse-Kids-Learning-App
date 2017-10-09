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

import com.madhouseapp.kidslearningapp.Adapters.AlphabetAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;
import com.madhouseapp.kidslearningapp.Object.AlphabetItem;

import java.util.ArrayList;
import java.util.List;

public class SpellingsActivity extends AppCompatActivity {

    private RecyclerView spellingsRecycler;
    private List<AlphabetItem> alphabetItemList;
    private AlphabetAdapter adapter;

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
        setContentView(R.layout.activity_spellings);

        alphabetItemList = new ArrayList<>();
        initList();
        adapter = new AlphabetAdapter(this, alphabetItemList);

        spellingsRecycler = (RecyclerView) findViewById(R.id.recycler_spellings);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        spellingsRecycler.setLayoutManager(centerZoomLayoutManager);
        spellingsRecycler.setItemAnimator(new DefaultItemAnimator());
        spellingsRecycler.setAdapter(adapter);

        previous = (Button) findViewById(R.id.previous_animals);
        play = (Button) findViewById(R.id.play_animals);
        next = (Button) findViewById(R.id.next_animals);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (centerZoomLayoutManager.findFirstVisibleItemPosition() != alphabetItemList.size() - 2) {
                    counter = centerZoomLayoutManager.findLastVisibleItemPosition() - 1;
                } else {
                    counter = centerZoomLayoutManager.findFirstVisibleItemPosition() + 1;

                }
                counter--;
                if (counter < 0) {
                    counter = alphabetItemList.size() - 1;
                    spellingsRecycler.scrollToPosition(counter);
                } else {
                    spellingsRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                counter++;
                if (counter > alphabetItemList.size() - 1) {
                    counter = 0;
                    spellingsRecycler.scrollToPosition(counter);
                } else {
                    spellingsRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        spellingsRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(spellingsRecycler);

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
        alphabetItemList.add(new AlphabetItem("First"));
        alphabetItemList.add(new AlphabetItem("Month"));
        alphabetItemList.add(new AlphabetItem("Minute"));
        alphabetItemList.add(new AlphabetItem("Light"));
        alphabetItemList.add(new AlphabetItem("Hello"));
        alphabetItemList.add(new AlphabetItem("World"));
        alphabetItemList.add(new AlphabetItem("Second"));
        alphabetItemList.add(new AlphabetItem("Mummy"));
        alphabetItemList.add(new AlphabetItem("Papa"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}