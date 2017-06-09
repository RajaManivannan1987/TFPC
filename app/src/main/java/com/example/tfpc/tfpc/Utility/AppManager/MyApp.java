package com.example.tfpc.tfpc.Utility.AppManager;

import android.support.multidex.MultiDexApplication;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.tfpc.tfpc.R;
import com.example.tfpc.tfpc.Utility.CommonClass.FontsOverride;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Im033 on 5/9/2017.
 */

public class MyApp extends MultiDexApplication {
    private static MyApp sInstanse;
    private RequestQueue nRequestQueue;

    public MyApp() {

    }

    public static synchronized MyApp getInstanse() {
        return sInstanse;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/lato_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        /*FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/lato_regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/lato_light.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/lato_bold.ttf");*/
        sInstanse = this;
    }

    public RequestQueue getnRequestQueue() {
        if (nRequestQueue == null) {
            nRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return nRequestQueue;
    }

    public void addToRequestQueue(Request request) {
        getnRequestQueue().add(request);
    }
}
