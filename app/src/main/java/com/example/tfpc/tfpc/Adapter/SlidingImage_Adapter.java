package com.example.tfpc.tfpc.Adapter;

/**
 * Created by Im033 on 5/22/2017.
 */

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tfpc.tfpc.Model.UpComingEvent;
import com.example.tfpc.tfpc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SlidingImage_Adapter extends PagerAdapter {
    private ArrayList<UpComingEvent> IMAGES;
    private LayoutInflater inflater;
    private Context context;

    public SlidingImage_Adapter(Context context, ArrayList<UpComingEvent> IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


        Picasso.with(context).load(IMAGES.get(position).getImage())
                .placeholder(R.drawable.circles)
                .error(R.drawable.cancel_button)
                .resize(250, 200).into(imageView);
//        imageView.setImageResource(IMAGES.get(position).getImage());
//        imageView.setBackgroundResource(IMAGES.get(position));

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
