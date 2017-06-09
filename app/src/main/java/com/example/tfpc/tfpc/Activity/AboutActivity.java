package com.example.tfpc.tfpc.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.tfpc.tfpc.R;
import com.example.tfpc.tfpc.Utility.ActionBar.BackNavigationBar;
import com.example.tfpc.tfpc.Utility.ActionBar.MenuCommonActivity;

/**
 * Created by Im033 on 5/8/2017.
 */

public class AboutActivity extends AppCompatActivity {
    private ImageView aboutBackButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);
        aboutBackButton = (ImageView) findViewById(R.id.aboutBackButton);
        aboutBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
