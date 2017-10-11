package com.madhouseapp.kidslearningapp.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.madhouseapp.kidslearningapp.Object.ImageItem;
import com.madhouseapp.kidslearningapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Akshansh on 11-10-2017.
 */

public class ShapesAdapter extends RecyclerView.Adapter<ShapesAdapter.ShapesViewHolder> {

    private Context context;
    private List<ImageItem> imageItemList;
    private Typeface jellyCrazies;

    public class ShapesViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        public ShapesViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.shape_name);
            image = view.findViewById(R.id.shape_image);
        }
    }

    public ShapesAdapter(Context context, List<ImageItem> imageItemList) {
        this.context = context;
        this.imageItemList = imageItemList;
        jellyCrazies = Typeface.createFromAsset(context.getAssets(), "fonts/jelly_crazies.ttf");
    }

    @Override
    public ShapesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shapes_item, parent, false);
        return new ShapesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShapesViewHolder holder, int position) {
        int pos = position % imageItemList.size();

        ImageItem imageItem = imageItemList.get(pos);
        holder.name.setTypeface(jellyCrazies);
        holder.name.setText(imageItem.getName());
        Picasso.with(context)
                .load(imageItem.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}