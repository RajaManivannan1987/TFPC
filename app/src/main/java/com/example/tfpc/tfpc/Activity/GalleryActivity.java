package com.example.tfpc.tfpc.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.tfpc.tfpc.Adapter.SlidingImage_Adapter;
import com.example.tfpc.tfpc.Interface.VolleyResponseListerner;
import com.example.tfpc.tfpc.Model.UpComingEvent;
import com.example.tfpc.tfpc.R;
import com.example.tfpc.tfpc.Utility.Webservice.FitchDataFromJson;
import com.viewpagerindicator.CirclePageIndicator;
import com.example.tfpc.tfpc.Utility.ActionBar.MenuCommonActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;

public class GalleryActivity extends MenuCommonActivity {
    private static ViewPager mPager;
    private String TAG = GalleryActivity.class.getName();
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private Gson gson = new Gson();
    private SlidingImage_Adapter adapter;
    ArrayList<UpComingEvent> listview = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_gallery);
        mPager = (ViewPager) findViewById(R.id.pager);
        adapter = new SlidingImage_Adapter(GalleryActivity.this, listview);
        mPager.setAdapter(adapter);
        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
        init();
    }

    private void loadData() {
        new FitchDataFromJson(GalleryActivity.this, TAG).getHomeData(new VolleyResponseListerner() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                Log.d(TAG, response.toString());
                listview.clear();
                if (response.getString("status").equalsIgnoreCase("1")) {
                    NUM_PAGES = response.getJSONObject("data").getJSONArray("recentpost").length() - 1;
                    for (int i = 0; i < response.getJSONObject("data").getJSONArray("recentpost").length(); i++) {
                        listview.add(gson.fromJson(response.getJSONObject("data").getJSONArray("recentpost").getJSONObject(i).toString(), UpComingEvent.class));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message, String title) {

            }
        });
    }


    private void init() {
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    mPager.setCurrentItem(currentPage, true);
                    currentPage = 0;
                } else
                    mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
