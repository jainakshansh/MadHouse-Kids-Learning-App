package com.madhouseapp.kidslearningapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.madhouseapp.kidslearningapp.Adapters.CategoryAdapter;
import com.madhouseapp.kidslearningapp.Object.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class LandingActivity extends AppCompatActivity {

    private RecyclerView categoryRecycler;
    private List<CategoryItem> categoryItemList;
    private CategoryAdapter categoryAdapter;

    private TextView learnToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
        learnToday = (TextView) findViewById(R.id.choose_landing);
        learnToday.setTypeface(typeface);

        categoryItemList = new ArrayList<>();
        initList();
        categoryAdapter = new CategoryAdapter(this, categoryItemList);

        categoryRecycler = (RecyclerView) findViewById(R.id.recycler_landing);
        RecyclerView.LayoutManager catLayMan = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(catLayMan);
        categoryRecycler.setItemAnimator(new DefaultItemAnimator());
        categoryRecycler.setAdapter(categoryAdapter);
        categoryRecycler.smoothScrollToPosition(1);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(categoryRecycler);
    }

    private void initList() {
        categoryItemList.add(new CategoryItem("", android.R.color.white));
        categoryItemList.add(new CategoryItem("Alphabets", R.drawable.alphabet_a));
        categoryItemList.add(new CategoryItem("Animals", R.drawable.tiger));
        categoryItemList.add(new CategoryItem("Fruits", R.drawable.grape));
        categoryItemList.add(new CategoryItem("Vegetables", R.drawable.cauli));
        categoryItemList.add(new CategoryItem("Vehicles", R.drawable.aeroplane));
        categoryItemList.add(new CategoryItem("", android.R.color.white));
    }
}
