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

public class VehiclesActivity extends AppCompatActivity {

    private RecyclerView vehiclesRecycler;
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
        setContentView(R.layout.activity_vehicles);

        imageItemList = new ArrayList<>();
        initList();
        adapter = new ImageAdapter(this, imageItemList);

        vehiclesRecycler = (RecyclerView) findViewById(R.id.recycler_vehicles);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        vehiclesRecycler.setLayoutManager(centerZoomLayoutManager);
        vehiclesRecycler.setItemAnimator(new DefaultItemAnimator());
        vehiclesRecycler.setAdapter(adapter);

        previous = (ImageView) findViewById(R.id.previous_vehicles);
        play = (ImageView) findViewById(R.id.play_vehicles);
        next = (ImageView) findViewById(R.id.next_vehicles);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                /*
                if (counter == 0) {
                    counter = imageItemList.size() - 2;
                    vehiclesRecycler.scrollToPosition(counter);
                }
                */
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
                counter++;
                /*
                if (counter == imageItemList.size() - 2) {
                    counter = 1;
                    vehiclesRecycler.scrollToPosition(counter);
                }
                */
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
    }

    private void initList() {
        //imageItemList.add(new ImageItem("", android.R.color.transparent));
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
        //imageItemList.add(new ImageItem("", android.R.color.transparent));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
