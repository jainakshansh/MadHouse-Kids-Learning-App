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

import com.madhouseapp.kidslearningapp.Adapters.ImageAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;
import com.madhouseapp.kidslearningapp.Object.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class AnimalsActivity extends AppCompatActivity {

    private RecyclerView animalRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter adapter;

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
        setContentView(R.layout.activity_animals);

        imageItemList = new ArrayList<>();
        initList();
        adapter = new ImageAdapter(this, imageItemList);

        animalRecycler = (RecyclerView) findViewById(R.id.recycler_animals);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        animalRecycler.setLayoutManager(centerZoomLayoutManager);
        animalRecycler.setItemAnimator(new DefaultItemAnimator());
        animalRecycler.setAdapter(adapter);

        previous = (ImageView) findViewById(R.id.previous_animals);
        play = (ImageView) findViewById(R.id.play_animals);
        next = (ImageView) findViewById(R.id.next_animals);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                /*
                if (counter == 0) {
                    counter = imageItemList.size() - 2;
                    animalRecycler.scrollToPosition(counter);
                }
                */
                if (counter < 0) {
                    counter = imageItemList.size() - 1;
                    animalRecycler.scrollToPosition(counter);
                } else {
                    animalRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                /*
                if (counter == imageItemList.size() - 2) {
                    counter = 1;
                    animalRecycler.scrollToPosition(counter);
                }
                */
                if (counter > imageItemList.size() - 1) {
                    counter = 0;
                    animalRecycler.scrollToPosition(counter);
                } else {
                    animalRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        animalRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(animalRecycler);
    }

    private void initList() {
        //imageItemList.add(new ImageItem("", android.R.color.transparent));
        imageItemList.add(new ImageItem("Alligator", R.drawable.alligator));
        imageItemList.add(new ImageItem("Bear", R.drawable.bear));
        imageItemList.add(new ImageItem("Elephant", R.drawable.elephant));
        imageItemList.add(new ImageItem("Lion", R.drawable.lion));
        imageItemList.add(new ImageItem("Monkey", R.drawable.monkey));
        imageItemList.add(new ImageItem("Panda", R.drawable.panda));
        imageItemList.add(new ImageItem("Rabbit", R.drawable.rabbit));
        imageItemList.add(new ImageItem("Snake", R.drawable.snake));
        imageItemList.add(new ImageItem("Squirrel", R.drawable.squirrel));
        imageItemList.add(new ImageItem("Tiger", R.drawable.tiger));
        imageItemList.add(new ImageItem("Zebra", R.drawable.zebra));
        //imageItemList.add(new ImageItem("", android.R.color.transparent));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
