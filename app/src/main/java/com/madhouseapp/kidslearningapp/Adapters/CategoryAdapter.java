package com.madhouseapp.kidslearningapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.madhouseapp.kidslearningapp.AlphabetsActivity;
import com.madhouseapp.kidslearningapp.AnimalsActivity;
import com.madhouseapp.kidslearningapp.FruitsActivity;
import com.madhouseapp.kidslearningapp.Object.CategoryItem;
import com.madhouseapp.kidslearningapp.R;
import com.madhouseapp.kidslearningapp.VegetablesActivity;
import com.madhouseapp.kidslearningapp.VehiclesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Akshansh on 30-09-2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<CategoryItem> categoryItemList;
    private Typeface jellyCrazies;

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView cat_name;
        private ImageView cat_image;
        private Button cat_button;

        public CategoryViewHolder(View view) {
            super(view);
            cat_name = view.findViewById(R.id.select_text);
            cat_image = view.findViewById(R.id.select_image);
            cat_button = view.findViewById(R.id.select_button);
        }
    }

    public CategoryAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
        jellyCrazies = Typeface.createFromAsset(context.getAssets(), "fonts/jelly_crazies.ttf");
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        CategoryItem categoryItem = categoryItemList.get(position);
        holder.cat_name.setTypeface(jellyCrazies);
        holder.cat_name.setText(categoryItem.getCat_name());
        Picasso.with(context)
                .load(categoryItem.getCat_image())
                .into(holder.cat_image);
        int[] colorArray = {R.color.colorPrimary, R.color.blueGray, R.color.vermillion, R.color.teal};
        final int pos = position % colorArray.length;
        holder.cat_button.setBackgroundColor(ContextCompat.getColor(context, colorArray[pos]));

        final int itemPos = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (itemPos) {
                    case 0:
                        context.startActivity(new Intent(context.getApplicationContext(), AlphabetsActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context.getApplicationContext(), AnimalsActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context.getApplicationContext(), FruitsActivity.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context.getApplicationContext(), VegetablesActivity.class));
                        break;
                    case 4:
                        context.startActivity(new Intent(context.getApplicationContext(), VehiclesActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }
}
