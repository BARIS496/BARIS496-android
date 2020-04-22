package com.example.bil496;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HistoryAdapter extends BaseAdapter {
    private LayoutInflater userInflater;
    private List<History> userList;

    public HistoryAdapter(Activity activity, List<History> userList) {
        userInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = userInflater.inflate(R.layout.activity_deneme, null);
        TextView textViewUserName = (TextView) lineView.findViewById(R.id.textViewUserName);
        TextView textViewSystemClock = (TextView) lineView.findViewById(R.id.textViewSystemClock);
        ImageView imageViewUserPicture = (ImageView) lineView.findViewById(R.id.image);

        History user = userList.get(i);
        textViewUserName.setText(user.getName().toString());
        textViewSystemClock.setText(user.getAmount().toString());

        imageViewUserPicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        /*if (user.isUserGender()) {
            imageViewUserPicture.setImageResource(R.drawable.women_profile);
        } else {
            imageViewUserPicture.setImageResource(R.drawable.man_profile);
        }*/

        return lineView;
    }
}