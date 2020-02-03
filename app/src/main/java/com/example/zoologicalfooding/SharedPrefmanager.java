package com.example.zoologicalfooding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefmanager {

    /**
     * Created by SukranSaygili on 01/02/2020.
     */

        //the constants
        private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
        private static final String KEY_USERNAME = "keyusername";
        private static final String KEY_LASTNAME = "keylastname";
        private static final String KEY_EMAIL = "keyemail";
        private static final String KEY_ADDRESS = "keyaddress";
        private static final String KEY_PASSWORD = "keypassword";

        private static SharedPrefmanager mInstance;
        private static Context mCtx;

        private SharedPrefmanager(Context context) {
            mCtx = context;
        }

        public static synchronized SharedPrefmanager getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new SharedPrefmanager(context);
            }
            return mInstance;
        }

        //method to let the user login
        //this method will store the user data in shared preferences
        public void userLogin(User user) {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_ADDRESS, user.getAddress());
            editor.putString(KEY_USERNAME, user.getFname());
            editor.putString(KEY_LASTNAME, user.getLname());
            editor.putString(KEY_EMAIL, user.getEmail());
            editor.apply();
        }

        //this method will checker whether user is already logged in or not
        public boolean isLoggedIn() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_USERNAME, null) != null;
        }

        //this method will give the logged in user
        public User getUser() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return new User(
                    sharedPreferences.getString(KEY_USERNAME, null),
                    sharedPreferences.getString(KEY_LASTNAME, null),
                    sharedPreferences.getString(KEY_ADDRESS, null),
                    sharedPreferences.getString(KEY_EMAIL, null),
                    sharedPreferences.getString(KEY_PASSWORD, null)
            );
        }

        //this method will logout the user
        public void logout() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
        }
    }

