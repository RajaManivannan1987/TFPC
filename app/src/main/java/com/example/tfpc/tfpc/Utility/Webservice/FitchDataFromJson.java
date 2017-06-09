package com.example.tfpc.tfpc.Utility.Webservice;

import android.content.Context;

import com.example.tfpc.tfpc.Interface.VolleyResponseListerner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Im033 on 5/24/2017.
 */

public class FitchDataFromJson {
    private String TAG;
    private Context context;
    private VolleyClass volleyClass;

    public FitchDataFromJson(Context context, String tag) {
        volleyClass = new VolleyClass(context, tag);
        this.TAG = tag;
        this.context = context;
    }

    public void getHomeData(final VolleyResponseListerner listerner) {
        getDataListerner(listerner, ConstantValue.SERVER_URL + "homepageimage");
    }

    public void getNewsList(final VolleyResponseListerner listerner) {
        String url = ConstantValue.SERVER_URL + "viewall";
        JSONObject jsonObject = new JSONObject();
        /*try {
//            jsonObject.put("category", "news");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        getDataListerner( listerner,url);
    }
    public void getAudioLaunchList(final VolleyResponseListerner listerner) {
        String url = ConstantValue.SERVER_URL + "viewall";
        JSONObject jsonObject = new JSONObject();
       /* try {
            jsonObject.put("category", "audio-launch");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        getDataListerner(listerner,url);
    }

    private void getDataListerner(final VolleyResponseListerner listerner, String url) {
        volleyClass.volleyNoProgressGet(url, new VolleyResponseListerner() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                listerner.onResponse(response);
            }

            @Override
            public void onError(String message, String title) {
                listerner.onError(message, title);
            }
        });
    }

    private void getPostDataListerner(String url, JSONObject jsonObject, final VolleyResponseListerner listerner) {
        volleyClass.volleyPostData(url, jsonObject, new VolleyResponseListerner() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                listerner.onResponse(response);
            }

            @Override
            public void onError(String message, String title) {
                listerner.onError(message, title);
            }
        });
    }
}
