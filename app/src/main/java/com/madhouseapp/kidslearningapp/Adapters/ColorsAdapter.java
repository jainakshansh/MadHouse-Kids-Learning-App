package com.madhouseapp.kidslearningapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madhouseapp.kidslearningapp.R;

/**
 * Created by Hemant on 10/9/2017.
 */

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorViewHolder> {
    private Context context;
    private int[] colors;
    private String[] colorNames;
    private Typeface jellyCrazies;

    public class ColorViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CardView cardView;

        public ColorViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.alphabet_text);
            cardView = itemView.findViewById(R.id.alphabet_card);
        }
    }

    public ColorsAdapter(Context context) {
        this.context = context;
        colors = context.getResources().getIntArray(R.array.colors);
        colorNames = context.getResources().getStringArray(R.array.colorNames);
        jellyCrazies = Typeface.createFromAsset(context.getAssets(), "fonts/jelly_crazies.ttf");
    }

    @Override
    public ColorsAdapter.ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alphabet_item, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorsAdapter.ColorViewHolder holder, int position) {
        holder.textView.setText(colorNames[position]);
        holder.textView.setTextSize(18);

        if (colorNames[position].equals("WHITE")) {
            holder.textView.setTextColor(Color.BLACK);
        } else {
            holder.textView.setTextColor(Color.WHITE);
        }
        holder.cardView.setBackgroundColor(colors[position]);
        holder.textView.setTypeface(jellyCrazies);
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }


}
