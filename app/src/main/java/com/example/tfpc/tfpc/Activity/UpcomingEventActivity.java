package com.example.tfpc.tfpc.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tfpc.tfpc.Adapter.UpcoimgEventAdapter;
import com.example.tfpc.tfpc.Interface.VolleyResponseListerner;
import com.example.tfpc.tfpc.Model.UpComingEvent;
import com.example.tfpc.tfpc.R;
import com.example.tfpc.tfpc.Utility.ActionBar.BackNavigationBar;
import com.example.tfpc.tfpc.Utility.Webservice.FitchDataFromJson;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Im033 on 6/7/2017.
 */

public class UpcomingEventActivity extends BackNavigationBar {
    private String TAG = UpcomingEventActivity.class.getName();
    private RecyclerView newsListView;
    private ArrayList<UpComingEvent> newsLists = new ArrayList<>();
    private UpcoimgEventAdapter adapter;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_news_list);
        setTitle("Upcoming Events");
        newsListView = (RecyclerView) findViewById(R.id.newsListRecyclerview);
        newsListView.setLayoutManager(new LinearLayoutManager(this));
        newsListView.setItemAnimator(new DefaultItemAnimator());
        adapter = new UpcoimgEventAdapter(UpcomingEventActivity.this, newsLists);
        newsListView.setAdapter(adapter);
        loadData();


    }

    private void loadData() {
        new FitchDataFromJson(UpcomingEventActivity.this, TAG).getNewsList(new VolleyResponseListerner() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                newsLists.clear();
                JSONArray array = response.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    newsLists.add(gson.fromJson(response.getJSONArray("data").getJSONObject(i).toString(), UpComingEvent.class));
//                    upComingEvents.add(gson.fromJson(response.getJSONObject("data").getJSONArray("latestnews").getJSONObject(i).toString(), UpComingEvent.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message, String title) {

            }
        });
    }
}
