package com.example.tfpc.tfpc.Utility.CommonClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Toast;

import com.example.tfpc.tfpc.Activity.DetailsActivity;
import com.example.tfpc.tfpc.Activity.Login;
import com.example.tfpc.tfpc.R;

/**
 * Created by Im033 on 5/9/2017.
 */

public class CommonMethods {
    public static void logoutIntent(Context context, Class<Login> activity) {
        context.startActivity(new Intent(context, activity).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public static void slidAnimation(Activity activity, View view, String transitionName){
        Intent intent = new Intent(activity, DetailsActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, transitionName);
        activity.startActivity(intent, options.toBundle());
    }
}
