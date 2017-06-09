package com.example.tfpc.tfpc.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfpc.tfpc.Adapter.AudioLaunchAdapter;
import com.example.tfpc.tfpc.Adapter.UpcoimgEventAdapter;
import com.example.tfpc.tfpc.Model.AudioLaunch;
import com.example.tfpc.tfpc.Model.UpComingEvent;
import com.google.gson.Gson;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.example.tfpc.tfpc.Interface.VolleyResponseListerner;
import com.example.tfpc.tfpc.R;
import com.example.tfpc.tfpc.Utility.ActionBar.MenuCommonActivity;
import com.example.tfpc.tfpc.Utility.Webservice.FitchDataFromJson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Im033 on 5/8/2017.
 */

public class HomeActivity extends MenuCommonActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private ImageView advertisementsImageView, upcomingMovieImageView, audioImageview;
    SliderLayout sliderLayout;
    private TextView homeDesTextView, seeAllNewsTextview;
    private String TAG = HomeActivity.class.getName();
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private RecyclerView latestNewsRecyclerView, audioLaunchRecyclerView;
    ArrayList<String> list;
    private List<String> description;
    ArrayList<UpComingEvent> upComingEventsList = new ArrayList<>();
    ArrayList<AudioLaunch> audioLaunchList = new ArrayList<>();
    private Gson gson = new Gson();
    private UpcoimgEventAdapter mAdapter;
    private AudioLaunchAdapter audiolaunchAdapter;
    String[] imageUrl;
    Handler handler = new Handler();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_home);
        setRecyclerView();
        loadData();
        seeAllNewsTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, UpcomingEventActivity.class));
            }
        });

        upcomingMovieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=uM7zTAMFRxc")));
            }
        });

    }

    private void setRecyclerView() {
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        list = new ArrayList<>();
        description = new ArrayList<>();


        audioImageview = (ImageView) findViewById(R.id.audioImageview);
        upcomingMovieImageView = (ImageView) findViewById(R.id.upcomingMovieImageView);
        seeAllNewsTextview = (TextView) findViewById(R.id.seeAllNewsTextview);
        homeDesTextView = (TextView) findViewById(R.id.homeDescriptionTextView);
        advertisementsImageView = (ImageView) findViewById(R.id.advertisementsImageView);
        latestNewsRecyclerView = (RecyclerView) findViewById(R.id.latestNewsRecyclerView);
        audioLaunchRecyclerView = (RecyclerView) findViewById(R.id.audioLaunchRecyclerView);
        latestNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        audioLaunchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        latestNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        audioLaunchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new UpcoimgEventAdapter(HomeActivity.this, upComingEventsList);
        audiolaunchAdapter = new AudioLaunchAdapter(HomeActivity.this, audioLaunchList);
        latestNewsRecyclerView.setAdapter(mAdapter);
        audioLaunchRecyclerView.setAdapter(audiolaunchAdapter);
    }

    private void loadData() {
        new FitchDataFromJson(HomeActivity.this, TAG).getHomeData(new VolleyResponseListerner() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                Log.d(TAG, response.toString());
                upComingEventsList.clear();
                audioLaunchList.clear();
                if (response.getString("status").equalsIgnoreCase("1")) {
                    for (int i = 0; i < response.getJSONObject("data").getJSONArray("newrelease").length(); i++) {
                        Picasso.with(HomeActivity.this)
                                .load(response.getJSONObject("data").getJSONArray("newrelease").getJSONObject(i).getString("image").toString())
                                .placeholder(R.drawable.logo)
                                .error(R.drawable.logo)
                                .into(advertisementsImageView);
                    }

                    for (int i = 0; i < response.getJSONObject("data").getJSONArray("recentpost").length(); i++) {
                        list.add(response.getJSONObject("data").getJSONArray("recentpost").getJSONObject(i).getString("image").toString());
                        description.add(response.getJSONObject("data").getJSONArray("recentpost").getJSONObject(i).getString("title").toString());
                    }
                    for (int i = 0; i < response.getJSONObject("data").getJSONArray("upcomingevents").length(); i++) {
                        upComingEventsList.add(gson.fromJson(response.getJSONObject("data").getJSONArray("upcomingevents").getJSONObject(i).toString(), UpComingEvent.class));
                    }
                    for (int i = 0; i < response.getJSONObject("data").getJSONArray("audiolaunch").length(); i++) {
                        audioLaunchList.add(gson.fromJson(response.getJSONObject("data").getJSONArray("audiolaunch").getJSONObject(i).toString(), AudioLaunch.class));
                    }
                    imageUrl = new String[response.getJSONObject("data").getJSONArray("audiolaunch").length()];
                    for (int i = 0; i < response.getJSONObject("data").getJSONArray("audiolaunch").length(); i++) {
                        imageUrl[i] = response.getJSONObject("data").getJSONArray("audiolaunch").getJSONObject(i).getString("image").toString();
                    }

                    Runnable runnable = new Runnable() {
                        int i = 0;
                        public void run() {
                            Picasso.with(HomeActivity.this)
                                    .load(imageUrl[i])
                                    .into(audioImageview);
                            i++;
                            if (i > imageUrl.length - 1) {
                                i = 0;
                            }
                            handler.postDelayed(this, 2000);
                        }
                    };
                    handler.postDelayed(runnable, 4000);

                    for (int i = 0; i < list.size(); i++) {
//                        homeDesTextView.setText("");
// homeDesTextView.setText(description.get(i));
                        TextSliderView textSliderView = new TextSliderView(HomeActivity.this);
                        textSliderView.description(description.get(i))
                                .image(list.get(i))
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(HomeActivity.this);
                        textSliderView.bundle(new Bundle());
                        /*textSliderView.getBundle()
                                .putInt("extra", Integer.parseInt(list.get(i)));*/
                        sliderLayout.addSlider(textSliderView);
                    }
                    /*for (String name : list) {
                        TextSliderView textSliderView = new TextSliderView(HomeActivity.this);
                        textSliderView.description(description.toString())
                                .image(name)
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(HomeActivity.this);
                        textSliderView.bundle(new Bundle());
                       *//* textSliderView.getBundle()
                                .putInt("extra", Integer.parseInt(name));*//*
                        sliderLayout.addSlider(textSliderView);
                    }*/
                    sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                    sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    sliderLayout.setCustomAnimation(new DescriptionAnimation());
                    sliderLayout.setDuration(5000);
                    sliderLayout.addOnPageChangeListener(HomeActivity.this);
                }
                mAdapter.notifyDataSetChanged();
                audiolaunchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message, String title) {

            }
        });
    }

    @Override
    protected void onStop() {
        sliderLayout.startAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
