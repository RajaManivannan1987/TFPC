package com.example.tfpc.tfpc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfpc.tfpc.Activity.DetailsActivity;
import com.example.tfpc.tfpc.Model.AudioLaunch;
import com.example.tfpc.tfpc.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

/**
 * Created by Im033 on 6/5/2017.
 */

public class AudioLaunchAdapter extends RecyclerView.Adapter<AudioLaunchAdapter.CustomHolder> {
    ArrayList<AudioLaunch> list;
    Context context;


    public AudioLaunchAdapter(Context context, ArrayList<AudioLaunch> homeModels) {
        this.context = context;
        this.list = homeModels;

    }


    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.audio_launch_list_item, parent, false);
        return new CustomHolder(view);
    }

    //    final float scale =context.getResources().getDisplayMetrics().density;
    Transformation transformation = new Transformation() {

        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth = (int) (400 * context.getResources().getDisplayMetrics().density + 0.5f);

            double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            int targetHeight = (int) (targetWidth * aspectRatio);
            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
// Same bitmap is returned if sizes are the same
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
    };

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.light_gray);
        } else {
            holder.itemView.setBackgroundResource(R.color.white_color);
        }
        Picasso.with(context)
                .load(list.get(position).getImage())
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo).transform(transformation)
                .into(holder.img);

        holder.des.setText(list.get(position).getTitle());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {
        TextView des, date;
        ImageView img;

        public CustomHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.audioImageview);
            des = (TextView) itemView.findViewById(R.id.audioDesTextview);
//            date = (TextView) itemView.findViewById(R.id.latestNewsDateTextView);
        }
    }
}
