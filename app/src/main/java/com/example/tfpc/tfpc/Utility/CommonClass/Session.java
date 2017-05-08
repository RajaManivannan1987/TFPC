package com.example.tfpc.tfpc.Utility.CommonClass;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by IM028 on 8/2/16.
 */
public class Session {
    private String TAG;
    private Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    private static String PREF_NAME = "tfpc";
    private static String userId = "userId";
    private static String name = "name";
    private static String token = "token";
    private static String photo = "photo";
    private static final String IS_LOGIN = "IsLoggedIn";


    public Session(Context context, String TAG) {
        this.context = context;
        this.TAG = "Session " + TAG;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createSession(String userId, String photo, String name, String token) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(this.userId, userId);
        editor.putString(this.name, name);
        editor.putString(this.token, token);
        editor.putString(this.photo, photo);
        editor.commit();
    }

    public String getUserId() {
        return pref.getString(userId, "");
    }

    public String getToken() {
        return pref.getString(token, "");
    }

    public String getPhoto() {
        return pref.getString(photo, "");
    }

    public String getName() {
        return pref.getString(name, "");
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }

    public boolean getIsLogin() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
