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
import android.widget.Toast;

import com.madhouseapp.kidslearningapp.Adapters.ImageAdapter;
import com.madhouseapp.kidslearningapp.Helper.CenterZoomLayoutManager;
import com.madhouseapp.kidslearningapp.Object.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class VehiclesActivity extends AppCompatActivity {

    private RecyclerView vehiclesRecycler;
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
        setContentView(R.layout.activity_vehicles);

        sounds = new int[]{R.raw.aeroplane, R.raw.autorickshaw, R.raw.cab, R.raw.camping_car, R.raw.car, R.raw.caravan, R.raw.cargotruck,
                R.raw.dliveryvan, R.raw.hotairbaloon, R.raw.icecreamtruck, R.raw.motorcycle, R.raw.scooter, R.raw.ship, R.raw.speedboat, R.raw.truck};

        imageItemList = new ArrayList<>();
        initList();
        adapter = new ImageAdapter(this, imageItemList);

        vehiclesRecycler = (RecyclerView) findViewById(R.id.recycler_vehicles);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        vehiclesRecycler.setLayoutManager(centerZoomLayoutManager);
        vehiclesRecycler.setItemAnimator(new DefaultItemAnimator());
        vehiclesRecycler.setAdapter(adapter);

        previous = (Button) findViewById(R.id.previous_vehicles);
        play = (Button) findViewById(R.id.play_vehicles);
        next = (Button) findViewById(R.id.next_vehicles);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (centerZoomLayoutManager.findFirstVisibleItemPosition() != imageItemList.size()-2) {
                    counter = centerZoomLayoutManager.findLastVisibleItemPosition() - 1;
                } else {
                    counter = centerZoomLayoutManager.findFirstVisibleItemPosition()+1;

                }
                counter--;
                if (counter < 0) {
                    counter = imageItemList.size() - 1;
                    vehiclesRecycler.scrollToPosition(counter);
                } else {
                    vehiclesRecycler.smoothScrollToPosition(counter);
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
                    vehiclesRecycler.scrollToPosition(counter);
                } else {
                    vehiclesRecycler.smoothScrollToPosition(counter);
                }
            }
        });

        vehiclesRecycler.smoothScrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(vehiclesRecycler);

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
        imageItemList.add(new ImageItem("Aeroplane", R.drawable.aeroplane));
        imageItemList.add(new ImageItem("Auto Rickshaw", R.drawable.auto));
        imageItemList.add(new ImageItem("Cab", R.drawable.cab));
        imageItemList.add(new ImageItem("Camping Car", R.drawable.campingcar));
        imageItemList.add(new ImageItem("Car", R.drawable.car));
        imageItemList.add(new ImageItem("Caravan", R.drawable.caravan));
        imageItemList.add(new ImageItem("Cargo", R.drawable.cargo));
        imageItemList.add(new ImageItem("Delivery Van", R.drawable.deliveryvan));
        imageItemList.add(new ImageItem("Hot Air Balloon", R.drawable.hotairballoon));
        imageItemList.add(new ImageItem("IceCream Truck", R.drawable.icecream));
        imageItemList.add(new ImageItem("Motorcycle", R.drawable.motorcycle));
        imageItemList.add(new ImageItem("Scooter", R.drawable.scooter));
        imageItemList.add(new ImageItem("Ship", R.drawable.ship));
        imageItemList.add(new ImageItem("Speed Boat", R.drawable.speedboat));
        imageItemList.add(new ImageItem("Truck", R.drawable.truck));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
