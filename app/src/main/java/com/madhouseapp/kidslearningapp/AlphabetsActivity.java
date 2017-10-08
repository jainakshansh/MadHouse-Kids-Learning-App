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
import android.widget.Button;

import com.madhouseapp.kidslearningapp.Adapters.AlphabetAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;
import com.madhouseapp.kidslearningapp.Object.AlphabetItem;

import java.util.ArrayList;
import java.util.List;

public class AlphabetsActivity extends AppCompatActivity {

    private List<AlphabetItem> alphabetsList;
    private RecyclerView alphabetRecycler;
    private AlphabetAdapter adapter;
    private CenterZoomLayoutManager centerZoomLayoutManager;

    private Button previous, play, next;
    private int counter = 0;

    private int[] sounds;
    private MediaPlayer mediaPlayer;
    private Animation zoomIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alphabets);

        sounds = new int[]{R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.h, R.raw.i, R.raw.j, R.raw.k,
                R.raw.l, R.raw.m, R.raw.n, R.raw.o, R.raw.p, R.raw.q, R.raw.r, R.raw.s, R.raw.t, R.raw.u, R.raw.v, R.raw.w,
                R.raw.x, R.raw.y, R.raw.z};

        alphabetsList = new ArrayList<>();
        initList();
        adapter = new AlphabetAdapter(this, alphabetsList);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        alphabetRecycler = (RecyclerView) findViewById(R.id.recycler_alphabets);
        previous = (Button) findViewById(R.id.previous_alphabets);
        play = (Button) findViewById(R.id.play_alphabets);
        next = (Button) findViewById(R.id.next_alphabets);

        alphabetRecycler.setLayoutManager(centerZoomLayoutManager);
        alphabetRecycler.setItemAnimator(new DefaultItemAnimator());
        alphabetRecycler.setAdapter(adapter);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                if (counter < 0) {
                    counter = alphabetsList.size() - 1;
                    alphabetRecycler.scrollToPosition(counter);
                } else {
                    alphabetRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                if (counter > alphabetsList.size() - 1) {
                    counter = 0;
                    alphabetRecycler.scrollToPosition(counter);
                } else {
                    alphabetRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        alphabetRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(alphabetRecycler);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[counter]);
                mediaPlayer.start();
            }
        });
    }

    private void initList() {
        alphabetsList.add(new AlphabetItem("A a"));
        alphabetsList.add(new AlphabetItem("B b"));
        alphabetsList.add(new AlphabetItem("C c"));
        alphabetsList.add(new AlphabetItem("D d"));
        alphabetsList.add(new AlphabetItem("E e"));
        alphabetsList.add(new AlphabetItem("F f"));
        alphabetsList.add(new AlphabetItem("G g"));
        alphabetsList.add(new AlphabetItem("H h"));
        alphabetsList.add(new AlphabetItem("I i"));
        alphabetsList.add(new AlphabetItem("J j"));
        alphabetsList.add(new AlphabetItem("K k"));
        alphabetsList.add(new AlphabetItem("L l"));
        alphabetsList.add(new AlphabetItem("M m"));
        alphabetsList.add(new AlphabetItem("N n"));
        alphabetsList.add(new AlphabetItem("O o"));
        alphabetsList.add(new AlphabetItem("P p"));
        alphabetsList.add(new AlphabetItem("Q q"));
        alphabetsList.add(new AlphabetItem("R r"));
        alphabetsList.add(new AlphabetItem("S s"));
        alphabetsList.add(new AlphabetItem("T t"));
        alphabetsList.add(new AlphabetItem("U u"));
        alphabetsList.add(new AlphabetItem("V v"));
        alphabetsList.add(new AlphabetItem("W w"));
        alphabetsList.add(new AlphabetItem("X x"));
        alphabetsList.add(new AlphabetItem("Y y"));
        alphabetsList.add(new AlphabetItem("Z z"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
