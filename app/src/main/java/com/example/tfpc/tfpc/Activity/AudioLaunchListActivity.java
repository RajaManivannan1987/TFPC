package com.example.tfpc.tfpc.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tfpc.tfpc.Adapter.AudioLaunchAdapter;
import com.example.tfpc.tfpc.Interface.VolleyResponseListerner;
import com.example.tfpc.tfpc.Model.AudioLaunch;
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

public class AudioLaunchListActivity extends BackNavigationBar {
    private String TAG = AudioLaunchListActivity.class.getName();
    private RecyclerView audioLaunchRecyclerView;
    private ArrayList<AudioLaunch> audiolaunchLists = new ArrayList<>();
    private AudioLaunchAdapter adapter;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_audiolaunch_list);
        setTitle("New Releases");

        audioLaunchRecyclerView = (RecyclerView) findViewById(R.id.audiolaunchListRecyclerview);
        audioLaunchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        audioLaunchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AudioLaunchAdapter(AudioLaunchListActivity.this, audiolaunchLists);
        audioLaunchRecyclerView.setAdapter(adapter);
        loadData();


    }

    private void loadData() {
        new FitchDataFromJson(AudioLaunchListActivity.this, TAG).getAudioLaunchList(new VolleyResponseListerner() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                audiolaunchLists.clear();
                JSONArray array = response.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    audiolaunchLists.add(gson.fromJson(response.getJSONArray("data").getJSONObject(i).toString(), AudioLaunch.class));
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
