package com.example.tfpc.tfpc.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tfpc.tfpc.R;
import com.example.tfpc.tfpc.Utility.ActionBar.MenuCommonActivity;
import com.example.tfpc.tfpc.Utility.Webservice.ConstantValue;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Im033 on 5/8/2017.
 */

public class EventsActivity extends MenuCommonActivity {
    ArrayList<String> actorsList;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_events);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        actorsList = new ArrayList<String>();

//        new JSONAsyncTask().execute(ConstantValue.SERVER_URL + "homepageimage");
        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {
                AnimateandSlideShow();
            }
        };
        int delay = 500;
        int period = 4000;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                mHandler.post(mUpdateResults);
            }
        }, delay, period);
    }

    private void AnimateandSlideShow() {
        viewFlipper.showNext();
    }

    private class JSONAsyncTask extends AsyncTask<String,Void,Boolean> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(EventsActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
/*
            try {

                //------------------>>
//                HttpGet httppost = new HttpGet(urls[0]);
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpResponse response = httpclient.execute(httppost);
//
//                // StatusLine stat = response.getStatusLine();
//                int status = response.getStatusLine().getStatusCode();
//
//                if (status == 200) {
//                    HttpEntity entity = response.getEntity();
//                    String data = EntityUtils.toString(entity);
//
//
//                    JSONObject jsono = new JSONObject(data);
//                    JSONArray jarray = jsono.getJSONArray("recentpost");
//
//                    for (int i = 0; i < jarray.length(); i++) {
//                        JSONObject object = jarray.getJSONObject(i);
//
//                        //  Actors actor = new Actors();
//                        actorsList.add(object.getString("image"));
//                        //   actor.setImage(object.getString("image"));
//                        Log.d("image: ", object.getString("image"));
//
//                        //   actorsList.add(actor);
//
//                    }
//                    return true;
//                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
*/
            return null;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            if (result == false) {
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

            } else {
                setFlipperImage(actorsList);
            }
        }

        private void setFlipperImage(ArrayList<String> actorsList) {
            for(int i=0;i<actorsList.size();i++){
                Log.i("Set Filpper Called", actorsList.get(i).toString()+"");
                ImageView image = new ImageView(getApplicationContext());
// image.setBackgroundResource(res);
                Picasso.with(EventsActivity.this)
                        .load(actorsList.get(i).toString())
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .into(image);
                viewFlipper.addView(image);
            }
        }
    }
}
