package com.example.bil496;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.widget.Toast;

public class ContainerActivity extends AppCompatActivity{

    //Container listelemek icin kullanacagimiz metod

    ArrayList<Double> listLat = new ArrayList<>();
    ArrayList<Double> listLng = new ArrayList<>();
    ArrayList<Integer> listId = new ArrayList<>();

    private MapView mapView;
    private RequestQueue mQueue;
    private String url = "http://restservices496.herokuapp.com/containers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoic3VrcmFuc2F5Z2lsaSIsImEiOiJjazdjYzZjYWEwYzhkM25wYTAybTVybHYwIn0.KBD1ETlxgvU-0AghYS7rhw");
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mQueue = Volley.newRequestQueue(this);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {

                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments.

                    }
                });
                // Initialize a new JsonArrayRequest instance
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

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


                                listLat.add((Double.valueOf(lat)));
                                listLng.add(Double.valueOf(lng));
                                listId.add(id);
                            }
                            //added mark
                            for(int i = 0; i < listLat.size(); i++){
                                MarkerOptions options = new MarkerOptions();
                                options.setTitle(String.valueOf(listId.get(i)));
                                options.position(new LatLng(listLat.get(i),listLng.get(i)));
                                mapboxMap.addMarker(options);
                            }

                            mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
                                    Intent gecisYap = new Intent(ContainerActivity.this, ContainerDetailsActivity.class);
                                    gecisYap.putExtra("id",marker.getTitle());
                                    startActivity(gecisYap);
                                    return true;
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
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}