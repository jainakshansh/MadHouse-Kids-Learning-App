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
import com.madhouseapp.kidslearningapp.Adapters.ShapesAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;
import com.madhouseapp.kidslearningapp.Object.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class ShapesActivity extends AppCompatActivity {
    private RecyclerView shapesRecycler;
    private List<ImageItem> imageItemList;
    private ShapesAdapter adapter;

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
        setContentView(R.layout.activity_shapes);

        sounds = new int[]{R.raw.circle, R.raw.triangle, R.raw.oval, R.raw.square, R.raw.rectangle, R.raw.hexagon,
                R.raw.pentagon, R.raw.diamond, R.raw.cylinder, R.raw.cube, R.raw.pyramid, R.raw.cone};

        imageItemList = new ArrayList<>();
        initList();
        adapter = new ShapesAdapter(this, imageItemList);

        shapesRecycler = (RecyclerView) findViewById(R.id.recycler_shapes);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        shapesRecycler.setLayoutManager(centerZoomLayoutManager);
        shapesRecycler.setItemAnimator(new DefaultItemAnimator());
        shapesRecycler.setAdapter(adapter);

        previous = (Button) findViewById(R.id.previous_shapes);
        play = (Button) findViewById(R.id.play_shapes);
        next = (Button) findViewById(R.id.next_shapes);

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
                    shapesRecycler.scrollToPosition(counter);
                } else {
                    shapesRecycler.smoothScrollToPosition(counter);
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
                    shapesRecycler.scrollToPosition(counter);
                } else {
                    shapesRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        shapesRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(shapesRecycler);

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

    private void initList() {
        imageItemList.add(new ImageItem("Circle", R.drawable.circle));
        imageItemList.add(new ImageItem("Triangle", R.drawable.triangle));
        imageItemList.add(new ImageItem("Oval", R.drawable.oval));
        imageItemList.add(new ImageItem("Square", R.drawable.square));
        imageItemList.add(new ImageItem("Rectangle", R.drawable.rectangle));
        imageItemList.add(new ImageItem("Hexagon", R.drawable.hexagon));
        imageItemList.add(new ImageItem("Pentagon", R.drawable.pentagon));
        imageItemList.add(new ImageItem("Diamond", R.drawable.diamond));
        imageItemList.add(new ImageItem("Cylinder", R.drawable.cylinder));
        imageItemList.add(new ImageItem("Cube", R.drawable.cube));
        imageItemList.add(new ImageItem("Pyramid", R.drawable.pyramid));
        imageItemList.add(new ImageItem("Cone", R.drawable.cone));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
