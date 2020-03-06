package com.example.bil496;

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

import java.util.ArrayList;
import android.widget.Toast;

public class ContainerActivity extends AppCompatActivity{

    //Container listelemek icin kullanacagimiz metod

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    ArrayList<Double> list = new ArrayList<Double>();

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

                        JSONObject food_container = response.getJSONObject(i);


                        int id = food_container.getInt("containerID");
                        String name = food_container.getString("name");
                        String type = food_container.getString("type");
                        String lng = food_container.getString("longitude");
                        String lat = food_container.getString("latitude");
                        String address = food_container.getString("address");
                        double weight = food_container.getDouble("weight");

                        mTextViewResult.append(String.valueOf(id) + ", " + name + ", "
                                + type + ", " + String.valueOf(lng) + ", " + String.valueOf(lat) + ", " + address + ", " +
                                String.valueOf(weight) + ", " +
                                "\n\n");

                        list.add((Double.valueOf(lng)));


                    }
                    for(int i = 0; i<list.size(); i++)
                        Toast.makeText(getApplicationContext(), list.get(i) + "" , Toast.LENGTH_LONG).show();
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
