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
import android.widget.Button;

import com.madhouseapp.kidslearningapp.Adapters.ImageAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;
import com.madhouseapp.kidslearningapp.Object.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class FruitsActivity extends AppCompatActivity {

    private RecyclerView fruitsRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter adapter;

    private CenterZoomLayoutManager centerZoomLayoutManager;

    private Button previous, play, next;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fruits);

        imageItemList = new ArrayList<>();
        initList();
        adapter = new ImageAdapter(this, imageItemList);

        fruitsRecycler = (RecyclerView) findViewById(R.id.recycler_fruits);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        fruitsRecycler.setLayoutManager(centerZoomLayoutManager);
        fruitsRecycler.setItemAnimator(new DefaultItemAnimator());
        fruitsRecycler.setAdapter(adapter);

        previous = (Button) findViewById(R.id.previous_fruits);
        play = (Button) findViewById(R.id.play_fruits);
        next = (Button) findViewById(R.id.next_fruits);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                /*
                if (counter == 0) {
                    counter = imageItemList.size() - 2;
                    fruitsRecycler.scrollToPosition(counter);
                }
                */
                if (counter < 0) {
                    counter = imageItemList.size() - 1;
                    fruitsRecycler.scrollToPosition(counter);
                } else {
                    fruitsRecycler.smoothScrollToPosition(counter);
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
                    fruitsRecycler.scrollToPosition(counter);
                }
                */
                if (counter > imageItemList.size() - 1) {
                    counter = 0;
                    fruitsRecycler.scrollToPosition(counter);
                } else {
                    fruitsRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        fruitsRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(fruitsRecycler);
    }

    private void initList() {
        //imageItemList.add(new ImageItem("", android.R.color.transparent));
        imageItemList.add(new ImageItem("Banana", R.drawable.banana));
        imageItemList.add(new ImageItem("Cherry", R.drawable.cherry));
        imageItemList.add(new ImageItem("Coconut", R.drawable.coconut));
        imageItemList.add(new ImageItem("Fig", R.drawable.dragon));
        imageItemList.add(new ImageItem("Grapes", R.drawable.grape));
        imageItemList.add(new ImageItem("Green Apple", R.drawable.greenapple));
        imageItemList.add(new ImageItem("Kiwi", R.drawable.kiwi));
        imageItemList.add(new ImageItem("Papaya", R.drawable.papaya));
        imageItemList.add(new ImageItem("Peach", R.drawable.peach));
        imageItemList.add(new ImageItem("Pineapple", R.drawable.pine));
        imageItemList.add(new ImageItem("Pomegranate", R.drawable.pomegranate));
        imageItemList.add(new ImageItem("Strawberry", R.drawable.strawberry));
        imageItemList.add(new ImageItem("Watermelon", R.drawable.watermelon));
        //imageItemList.add(new ImageItem("", android.R.color.transparent));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
