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

public class VegetablesActivity extends AppCompatActivity {

    private RecyclerView vegetablesRecycler;
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
        setContentView(R.layout.activity_vegetables);

        imageItemList = new ArrayList<>();
        initList();
        adapter = new ImageAdapter(this, imageItemList);

        vegetablesRecycler = (RecyclerView) findViewById(R.id.recycler_vegetables);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        vegetablesRecycler.setLayoutManager(centerZoomLayoutManager);
        vegetablesRecycler.setItemAnimator(new DefaultItemAnimator());
        vegetablesRecycler.setAdapter(adapter);

        previous = (ImageView) findViewById(R.id.previous_vegetables);
        play = (ImageView) findViewById(R.id.play_vegetables);
        next = (ImageView) findViewById(R.id.next_vegetables);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                /*
                if (counter == 0) {
                    counter = imageItemList.size() - 2;
                    vegetablesRecycler.scrollToPosition(counter);
                }
                */
                if (counter < 0) {
                    counter = imageItemList.size() - 1;
                    vegetablesRecycler.scrollToPosition(counter);
                } else {
                    vegetablesRecycler.smoothScrollToPosition(counter);
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
                    vegetablesRecycler.scrollToPosition(counter);
                }
                */
                if (counter > imageItemList.size() - 1) {
                    counter = 0;
                    vegetablesRecycler.scrollToPosition(counter);
                } else {
                    vegetablesRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        vegetablesRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(vegetablesRecycler);
    }

    private void initList() {
        //imageItemList.add(new ImageItem("", android.R.color.transparent));
        imageItemList.add(new ImageItem("Artichokes", R.drawable.artichokes));
        imageItemList.add(new ImageItem("Asparagus", R.drawable.asparagus));
        imageItemList.add(new ImageItem("Beans & Peas", R.drawable.beansandpeas));
        imageItemList.add(new ImageItem("Beetroot", R.drawable.beet));
        imageItemList.add(new ImageItem("Blue Cabbage", R.drawable.bluecabbage));
        imageItemList.add(new ImageItem("Broccoli", R.drawable.broccolli));
        imageItemList.add(new ImageItem("Brussels Sprouts", R.drawable.brusselssprout));
        imageItemList.add(new ImageItem("Carrot", R.drawable.carrot));
        imageItemList.add(new ImageItem("Cauliflower", R.drawable.cauli));
        imageItemList.add(new ImageItem("Celery", R.drawable.celery));
        imageItemList.add(new ImageItem("Chili Peppers", R.drawable.chilipeppers));
        imageItemList.add(new ImageItem("Chinese Cabbage", R.drawable.chinesecabbage));
        imageItemList.add(new ImageItem("Corn", R.drawable.corn));
        imageItemList.add(new ImageItem("Cucumbers", R.drawable.cucumbers));
        imageItemList.add(new ImageItem("Dil", R.drawable.dil));
        imageItemList.add(new ImageItem("Eggplant", R.drawable.eggplant));
        imageItemList.add(new ImageItem("Garlic", R.drawable.garlic));
        imageItemList.add(new ImageItem("Green Cabbage", R.drawable.greencabbage));
        imageItemList.add(new ImageItem("Lemon", R.drawable.lemon));
        imageItemList.add(new ImageItem("Lettuce", R.drawable.lettuce));
        imageItemList.add(new ImageItem("Onions", R.drawable.onions));
        imageItemList.add(new ImageItem("Parseley", R.drawable.parseley));
        imageItemList.add(new ImageItem("Peppers", R.drawable.peppers));
        imageItemList.add(new ImageItem("Potatoes", R.drawable.potatoes));
        imageItemList.add(new ImageItem("Radish", R.drawable.radish));
        imageItemList.add(new ImageItem("Red Cabbage", R.drawable.redcabbage));
        imageItemList.add(new ImageItem("Squashes", R.drawable.squashes));
        imageItemList.add(new ImageItem("Tomatoes", R.drawable.tomatoes));
        imageItemList.add(new ImageItem("Zuccini", R.drawable.zuccini));
        //imageItemList.add(new ImageItem("", android.R.color.transparent));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
