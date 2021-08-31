package com.example.pdoran.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pdoran.R;
import com.example.pdoran.activity.GalleryActivity;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private ArrayList<String> myDataSet;
    private Activity activity;


    static class GalleryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;;
        GalleryViewHolder(CardView v){
            super(v);
            cardView=v;
        }
    }


    public GalleryAdapter(Activity activity, ArrayList<String> dataSet) {
        myDataSet = dataSet;
        this.activity=activity;
    }


    @Override
    public GalleryAdapter.GalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

       CardView cardView= (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gallery, viewGroup, false);
       final GalleryViewHolder GalleryViewHolder=new GalleryViewHolder(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultintent = new Intent();
                resultintent.putExtra("profilePath",myDataSet.get(GalleryViewHolder.getAdapterPosition()));
                activity.setResult(Activity.RESULT_OK, resultintent);
                activity.finish();
            }
        });
        return GalleryViewHolder;
    }


    @Override
    public void onBindViewHolder(final GalleryViewHolder viewHolder, int position) {
        CardView cardView=viewHolder.cardView;

        ImageView imageView=cardView.findViewById(R.id.imageView);
        Glide.with(activity)
                .load(myDataSet.get(position))
                .centerCrop()
                .override(500)
                .into(imageView);
    }


    @Override
    public int getItemCount() {
        return myDataSet.size();
    }
}