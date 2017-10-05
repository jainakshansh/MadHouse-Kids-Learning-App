package com.madhouseapp.kidslearningapp;

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
import android.widget.ImageView;

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

    private ImageView previous, play, next;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alphabets);

        alphabetsList = new ArrayList<>();
        initList();
        adapter = new AlphabetAdapter(this, alphabetsList);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        alphabetRecycler = (RecyclerView) findViewById(R.id.recycler_alphabets);
        previous = (ImageView) findViewById(R.id.previous_alphabets);
        play = (ImageView) findViewById(R.id.play_alphabets);
        next = (ImageView) findViewById(R.id.next_alphabets);

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
