package com.example.tfpc.tfpc.Utility.ActionBar;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tfpc.tfpc.R;

/**
 * Created by Im033 on 5/11/2017.
 */

public class BackNavigationBar extends AppCompatActivity {
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private TextView titleTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_actionbar);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        toolbar = (Toolbar) findViewById(R.id.backToolbar);
        titleTextView = (TextView) toolbar.findViewById(R.id.backToolbarTitleTextView);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void setView(int layoutId) {
        frameLayout = (FrameLayout) findViewById(R.id.backActivityFrameLayout);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(layoutId, null, false);
        frameLayout.addView(activityView);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }
}
