package com.example.zoologicalfooding;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContainerActivity extends AppCompatActivity {

    //Container listelemek icin kullanacagimiz metod

    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mTextViewResult = findViewById(R.id.text_view_result);


        mQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

    private void jsonParse() {

        String url = "http://restservices496.herokuapp.com/containers";

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
                        JSONObject containers = response.getJSONObject(i);

                        int id = containers.getInt("container_id");
                        String name = containers.getString("name");
                        String type = containers.getString("type");
                        int lng = containers.getInt("lng");
                        int lat = containers.getInt("lat");
                        String address = containers.getString("address");
                        double weight = containers.getDouble("weight");

                        mTextViewResult.append(String.valueOf(id) + ", " + name + ", "
                                + type + ", " + String.valueOf(lng) + ", " + String.valueOf(lat) + ", " + address + ", " +
                                String.valueOf(weight) +
                                "\n\n");
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
