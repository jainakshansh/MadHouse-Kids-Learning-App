package com.madhouseapp.kidslearningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private ImageView share, rate;

    private SharedPreferences sharedPreferences;
    int appOpened = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        appOpened = sharedPreferences.getInt("appOpened", 0);
        appOpened++;
        editor.putInt("appOpened", appOpened);
        editor.apply();

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

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(categoryRecycler);

        share = (ImageView) findViewById(R.id.share_app);
        rate = (ImageView) findViewById(R.id.rate_app);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey, check out this amazing kids learning app at: https://play.google.com/store/apps/details?id=com.madhouseapp.kidslearningapp");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.madhouseapp.kidslearningapp")));
            }
        });
    }

    private void initList() {
        categoryItemList.add(new CategoryItem("Alphabets", R.drawable.alphabet_a));
        categoryItemList.add(new CategoryItem("Animals", R.drawable.tiger));
        categoryItemList.add(new CategoryItem("Fruits", R.drawable.grape));
        categoryItemList.add(new CategoryItem("Vegetables", R.drawable.cauli));
        categoryItemList.add(new CategoryItem("Vehicles", R.drawable.aeroplane));
        categoryItemList.add(new CategoryItem("Shapes", R.drawable.pyramid));
        categoryItemList.add(new CategoryItem("Colors", R.drawable.colors));
        categoryItemList.add(new CategoryItem("Objects", R.drawable.desk_chair));
        categoryItemList.add(new CategoryItem("Spellings", R.drawable.spelling_main));
    }

    @Override
    public void onBackPressed() {
        if (appOpened % 10 == 0) {
            MadHouseDialog dialog = new MadHouseDialog(LandingActivity.this);
            dialog.show();
        } else {
            finish();
        }
    }
}
