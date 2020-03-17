package com.example.bil496;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContainerDetailsActivity extends AppCompatActivity {

    private TextView nameText, typeText, weightText;
    private RequestQueue mQueue;
    private RequestQueue mQueue2;
    private Button donation;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_container_details);
        nameText = findViewById(R.id.nameText);
        typeText = findViewById(R.id.typeText);
        weightText = findViewById(R.id.weightText);

        mQueue = Volley.newRequestQueue(this);
        mQueue2 = Volley.newRequestQueue(this);

        Bundle gelenVeri=getIntent().getExtras();
        final int deger = gelenVeri.getInt("anahtar");


        String url = "http://restservices496.herokuapp.com/containers";
        String fillsUrl = "http://restservices496.herokuapp.com/fills";

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Do something with response
                //mTextView.setText(response.toString());

                // Process the JSON
                try{
                    // Loop through the array elements
                    for(int i=0;i<response.length();i++){
                        // Get current json object
                        JSONObject food_container = response.getJSONObject(i);

                        int id = food_container.getInt("containerID");
                        String name = food_container.getString("name");
                        String type = food_container.getString("type");
                        String lng = food_container.getString("longitude");
                        String lat = food_container.getString("latitude");
                        String address = food_container.getString("address");
                        double weight = food_container.getDouble("weight");

                        if(id == deger){
                            nameText.append(name + "\n\n");
                            typeText.append(type + "\n\n");
                            weightText.append(String.valueOf(weight) + "\n\n");

                        }
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
