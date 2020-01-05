package com.example.sepatuu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharePrefManager {
    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_ID = "keyid";
    private static final String KEY_ID_BELANJA = "keyidbelanja";
    private static final String KEY_ALAMAT = "keyalamat";
    private static final String KEY_JUMLAH = "keyjumlah";
    private static final String KEY_TOTAL = "keytotal";
    private static final String KEY_CHOSE = "keypilihan";

    private static SharePrefManager mInstance;
    private static Context ctx;

    private SharePrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharePrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharePrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(user user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_GENDER, user.getGender());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public user getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new user(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_GENDER, null)
        );
    }

    //method tambah orderan
    public dodolan dodolan(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new dodolan(
                sharedPreferences.getInt(KEY_ID_BELANJA, -1),
                sharedPreferences.getString(KEY_ALAMAT, null),
                sharedPreferences.getInt(KEY_JUMLAH, -1),
                sharedPreferences.getString(KEY_TOTAL, null),
                sharedPreferences.getString(KEY_CHOSE, null)
        );
    }

    public dodolan getdodol() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new dodolan(
                sharedPreferences.getInt(KEY_ID_BELANJA, -1),
                sharedPreferences.getString(KEY_ALAMAT, null),
                sharedPreferences.getInt(KEY_JUMLAH, -1),
                sharedPreferences.getString(KEY_TOTAL, null),
                sharedPreferences.getString(KEY_CHOSE, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, activity_login.class));
    }
}
