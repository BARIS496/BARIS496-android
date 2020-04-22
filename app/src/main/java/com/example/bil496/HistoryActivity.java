package com.example.bil496;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class HistoryActivity extends Activity {

    final List<History> users = new ArrayList<History>();

    private ArrayList<String> list = new ArrayList<>();
    private String url = "https://restservices496.herokuapp.com/donates";
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final ListView listView = (ListView) findViewById(R.id.listView);
        final HistoryAdapter adapter = new HistoryAdapter(this, users);

        Bundle gelenVeri = getIntent().getExtras();
        final String deger = gelenVeri.getString("container_id");
        //final String deger = "2717";


        mQueue = Volley.newRequestQueue(this);
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    // Loop through the array elements
                    for(int i=0;i<response.length();i++){
                        // Get current json object
                        JSONObject foodcontainer_generator = response.getJSONObject(i);

                        int id = foodcontainer_generator.getInt("donatesID");
                        String type = foodcontainer_generator.getString("foodType");
                        String amount = foodcontainer_generator.getString("amountStr");
                        String liked = foodcontainer_generator.getString("likedStr");
                        int container = foodcontainer_generator.getInt("containerId");
                        String fullName = foodcontainer_generator.getString("fullName");

                        //if(container == Integer.parseInt(deger)){
                            users.add(new History(0,fullName,amount));
                            listView.setAdapter(adapter);

                        //}
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        error.printStackTrace();
                    }
                }
        );
        // Add JsonArrayRequest to the RequestQueue
        mQueue.add(jsonArrayRequest);

    }
}

