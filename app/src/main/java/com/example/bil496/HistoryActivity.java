package com.example.bil496;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class HistoryActivity extends AppCompatActivity {
    private TextView fullName, foodName, mail, time;
    private RequestQueue mQueue;
    private int id;
    private int cont;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_history);
        fullName = findViewById(R.id.sat1sut1);
        foodName = findViewById(R.id.sat1sut2);
        mail = findViewById(R.id.sat1sut3);
        time = findViewById(R.id.sat1sut4);

        mQueue = Volley.newRequestQueue(this);

        Bundle gelenVeri = getIntent().getExtras();
        final String deger = gelenVeri.getString("id");

        String url = "https://restservices496.herokuapp.com/donates";

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    // Loop through the array elements
                    for(int i=0;i<response.length();i++){
                        // Get current json object
                        JSONObject donateTable_generator = response.getJSONObject(i);

                        int id2 = donateTable_generator.getInt("donatesID");
                        id = donateTable_generator.getInt("containerID");
                        String name = donateTable_generator.getString("fullName");
                        String food = donateTable_generator.getString("donateFoodName");
                        String mailAdd = donateTable_generator.getString("donaterMail");
                        String timeDate = donateTable_generator.getString("donateTime");

                        if(id == Integer.parseInt(deger)){
                            cont = id;
                            fullName.append(name + "\n\n");
                            foodName.append(food + "\n\n");
                            mail.append((mailAdd + "\n\n"));
                            time.append(timeDate + "\n\n");
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
    public int getCont(){
        return cont;
    }
    public void setCont(int id) {
        this.cont = id;
    }
}

