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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.madhouseapp.kidslearningapp.Adapters.SpellingAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;
import com.madhouseapp.kidslearningapp.Object.AlphabetItem;

import java.util.ArrayList;
import java.util.List;

public class SpellingsActivity extends AppCompatActivity {

    private RecyclerView spellingsRecycler;
    private List<AlphabetItem> alphabetItemList;
    private SpellingAdapter adapter;

    private CenterZoomLayoutManager centerZoomLayoutManager;

    private Button previous, play, next;
    private int counter = 0;

    private AdView adView;

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

        sounds = new int[]{R.raw.all, R.raw.bat, R.raw.cat, R.raw.dad, R.raw.dog, R.raw.hen, R.raw.hello,
                R.raw.mom, R.raw.one, R.raw.papa, R.raw.pet, R.raw.rat, R.raw.sun, R.raw.toy, R.raw.yes};
        adView = (AdView) findViewById(R.id.spellings_ad);
        AdRequest adRequest = new AdRequest.Builder()
                .tagForChildDirectedTreatment(true)
                .build();
        adView.loadAd(adRequest);
        alphabetItemList = new ArrayList<>();
        initList();
        adapter = new SpellingAdapter(this, alphabetItemList);

        spellingsRecycler = (RecyclerView) findViewById(R.id.recycler_spellings);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        spellingsRecycler.setLayoutManager(centerZoomLayoutManager);
        spellingsRecycler.setItemAnimator(new DefaultItemAnimator());
        spellingsRecycler.setAdapter(adapter);

        previous = (Button) findViewById(R.id.previous_spellings);
        play = (Button) findViewById(R.id.play_spellings);
        next = (Button) findViewById(R.id.next_spellings);

        counter = Integer.MAX_VALUE / 2;

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                counter--;
                spellingsRecycler.smoothScrollToPosition(counter);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                counter++;
                spellingsRecycler.smoothScrollToPosition(counter);
            }
        });

        spellingsRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(spellingsRecycler);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                int pos = counter % alphabetItemList.size();
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[pos]);
                mediaPlayer.start();

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initList() {
        alphabetItemList.add(new AlphabetItem("All"));
        alphabetItemList.add(new AlphabetItem("Bat"));
        alphabetItemList.add(new AlphabetItem("Cat"));
        alphabetItemList.add(new AlphabetItem("Dad"));
        alphabetItemList.add(new AlphabetItem("Dog"));
        alphabetItemList.add(new AlphabetItem("Hen"));
        alphabetItemList.add(new AlphabetItem("Hello"));
        alphabetItemList.add(new AlphabetItem("Mom"));
        alphabetItemList.add(new AlphabetItem("One"));
        alphabetItemList.add(new AlphabetItem("Papa"));
        alphabetItemList.add(new AlphabetItem("Pet"));
        alphabetItemList.add(new AlphabetItem("Rat"));
        alphabetItemList.add(new AlphabetItem("Sun"));
        alphabetItemList.add(new AlphabetItem("Toy"));
        alphabetItemList.add(new AlphabetItem("Yes"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}