package com.madhouseapp.kidslearningapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class AlphabetsActivity extends AppCompatActivity {

    private List<String> alphabetsList;

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
    }

    private void initList() {
        alphabetsList.add("A a");
        alphabetsList.add("B b");
        alphabetsList.add("C c");
        alphabetsList.add("D d");
        alphabetsList.add("E e");
        alphabetsList.add("F f");
        alphabetsList.add("G g");
        alphabetsList.add("H h");
        alphabetsList.add("I i");
        alphabetsList.add("J j");
        alphabetsList.add("K k");
        alphabetsList.add("L l");
        alphabetsList.add("M m");
        alphabetsList.add("N n");
        alphabetsList.add("O o");
        alphabetsList.add("P p");
        alphabetsList.add("Q q");
        alphabetsList.add("R r");
        alphabetsList.add("S s");
        alphabetsList.add("T t");
        alphabetsList.add("U u");
        alphabetsList.add("V v");
        alphabetsList.add("W w");
        alphabetsList.add("X x");
        alphabetsList.add("Y y");
        alphabetsList.add("Z z");
    }
}
