package com.example.bil496;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import android.widget.Toast;


public class ContainerDetailsActivity extends AppCompatActivity {

    private TextView nameText, addText, weightText, doluText, typeText, estimate;
    private RequestQueue mQueue;
    private Button button;
    private Button button2;
    private Button button3;
    private int id;
    private int cont;
    private ArrayList<String> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_container_details);
        nameText = findViewById(R.id.name);
        addText = findViewById(R.id.address);
        weightText = findViewById(R.id.agirlik);
        doluText = findViewById(R.id.dolu);
        typeText = findViewById(R.id.foodType);
        estimate = findViewById(R.id.estimate);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.historyButton);
        button3 = findViewById(R.id.commentButton);

        mQueue = Volley.newRequestQueue(this);

        Bundle gelenVeri = getIntent().getExtras();
        final String deger = gelenVeri.getString("id");

        String url = "http://restservices496.herokuapp.com/containers";

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

                        id = foodcontainer_generator.getInt("containerID");
                        String name = foodcontainer_generator.getString("name");
                        String type = foodcontainer_generator.getString("type");
                        String lng = foodcontainer_generator.getString("longitude");
                        String lat = foodcontainer_generator.getString("latitude");
                        String address = foodcontainer_generator.getString("address");
                        double weight = foodcontainer_generator.getDouble("weight");
                        String estimation = foodcontainer_generator.getString("estimation");

                        if(id == Integer.parseInt(deger)){
                            cont = id;
                            nameText.append(" Container Name: " + name + "\n\n");
                            typeText.append(" Type: " + type + "\n\n");
                            addText.append((" Address:" + address + "\n\n"));
                            weightText.append(" Weight: " + String.valueOf(weight) + "\n\n");
                            list.add(deger);
                            //max agirlik 5000 gram
                            double yuzde = (weight * 100) / 5000;
                            doluText.append(" Full Amount: % " + Math.floor(yuzde * 100) / 100);
                            estimate.append("Estimated End Time:" + estimation);

                        }
                    }
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Butona tıklandığında ne yapmasını gerektiğini belirttik
                            Intent gecisYap = new Intent(ContainerDetailsActivity.this, DonationActivity.class);
                            gecisYap.putExtra("container_id", deger);
                            startActivity(gecisYap);
                        }
                    });
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Butona tıklandığında ne yapmasını gerektiğini belirttik
                            Intent gecisYap = new Intent(ContainerDetailsActivity.this, HistoryActivity.class);
                            gecisYap.putExtra("container_id", list.get(0) );
                            startActivity(gecisYap);
                        }
                    });
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Toast.makeText(ContainerDetailsActivity.this, "Comment Adding!", Toast.LENGTH_SHORT).show();
                        }
                    });

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
