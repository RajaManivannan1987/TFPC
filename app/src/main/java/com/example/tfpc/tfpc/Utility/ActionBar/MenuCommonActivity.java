package com.example.tfpc.tfpc.Utility.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tfpc.tfpc.Activity.AboutActivity;
import com.example.tfpc.tfpc.Activity.ContactActivity;
import com.example.tfpc.tfpc.Activity.DocumentsActivity;
import com.example.tfpc.tfpc.Activity.EventsActivity;
import com.example.tfpc.tfpc.Activity.GalleryActivity;
import com.example.tfpc.tfpc.Activity.HomeActivity;
import com.example.tfpc.tfpc.Activity.MemberActivity;
import com.example.tfpc.tfpc.Adapter.CommonActionBarListAdapter;
import com.example.tfpc.tfpc.R;
import com.example.tfpc.tfpc.Utility.CommonClass.Session;


/**
 * Created by IM028 on 8/2/16.
 */
public class MenuCommonActivity extends AppCompatActivity {
    private static final String TAG = "MenuCommonActivity";
    private Toolbar toolbar;
    private FrameLayout frameLayout, menuActivityFrameLayout;
    private DrawerLayout drawerLayout;
    private ImageView menuImageView, menuHeaderImageView;
    private TextView menuHeaderTextView;
    private ListView menuListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_menu_activity_layout);


        toolbar = (Toolbar) findViewById(R.id.commonMenuActivityToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.commonMenuActivityDrawerLayout);
        menuImageView = (ImageView) findViewById(R.id.menu);

        menuListView = (ListView) findViewById(R.id.commonMenuActivityDrawerListView);
        
        menuActivityFrameLayout = (FrameLayout) findViewById(R.id.menuActivityFrameLayout);

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        View headerView = getLayoutInflater().inflate(R.layout.commom_navigation_heading, null, false);
        menuHeaderImageView = (ImageView) headerView.findViewById(R.id.profile_image);
        menuHeaderTextView = (TextView) headerView.findViewById(R.id.commonNavigationHeadingTextView);
//        menuHeaderTextView.setText(new Session(this, "MenuCommonActivity").getName());

        menuListView.setAdapter(new CommonActionBarListAdapter(this));
        menuListView.addHeaderView(headerView);
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    TextView nameTextView = (TextView) view.findViewById(R.id.commonNavigationItemTextView);
                    switch (nameTextView.getText().toString().toLowerCase().trim()) {
                        case "home":
                            startActivity(new Intent(MenuCommonActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                            break;
                        case "about tfpc":
                            startActivity(new Intent(MenuCommonActivity.this, AboutActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                            break;
                        case "events":
                            startActivity(new Intent(MenuCommonActivity.this, EventsActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                            break;
                        case "gallery":
                            startActivity(new Intent(MenuCommonActivity.this, GalleryActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                            break;
                        case "member search":
                            startActivity(new Intent(MenuCommonActivity.this, MemberActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                            break;
                        case "useful documents":
                            startActivity(new Intent(MenuCommonActivity.this, DocumentsActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                            break;
                        case "contact us":
                            startActivity(new Intent(MenuCommonActivity.this, ContactActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                            break;
                        default:
                            break;
                    }
                    if (drawerLayout.isDrawerOpen(Gravity.LEFT))
                        drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });

    }

    public void setView(int viewLayout) {
        frameLayout = (FrameLayout) findViewById(R.id.commonMenuActivityFrameLayout);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(viewLayout, null, false);
        frameLayout.addView(activityView);
    }

    public void setTitle(String title) {
//        titleTextView.setText(title);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

}
