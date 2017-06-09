package com.example.tfpc.tfpc.Adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfpc.tfpc.Model.UpComingEvent;
import com.example.tfpc.tfpc.R;
import com.example.tfpc.tfpc.Utility.CommonClass.CommonMethods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Im033 on 6/5/2017.
 */

public class UpcoimgEventAdapter extends RecyclerView.Adapter<UpcoimgEventAdapter.CustomHolder> {
    ArrayList<UpComingEvent> list;
    Activity context;


    public UpcoimgEventAdapter(Activity context, ArrayList<UpComingEvent> homeModels) {
        this.context = context;
        this.list = homeModels;
    }


    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.latest_news_list_item, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomHolder holder, int position) {
        if (position % 2 == 0) {
            holder.latestNewsCardView.setBackgroundResource(R.color.light_gray);
        } else {
            holder.latestNewsCardView.setBackgroundResource(R.color.white_color);
        }
        Picasso.with(context)
                .load(list.get(position).getImage())
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(holder.img);
        holder.des.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDate());
        holder.latestNewsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonMethods.slidAnimation(context, holder.latestNewsCardView, context.getResources().getString(R.string.transition_string));
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonMethods.slidAnimation(context, holder.img, context.getResources().getString(R.string.transition_string));
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
        CardView latestNewsCardView;

        public CustomHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.latestNewsImageview);
            des = (TextView) itemView.findViewById(R.id.latestNewsdesTextView);
            date = (TextView) itemView.findViewById(R.id.latestNewsDateTextView);
            latestNewsCardView = (CardView) itemView.findViewById(R.id.latestNewsCardView);
        }
    }
}
