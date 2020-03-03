package com.example.bil496;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                    }
                });
            }
        });

    }
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    public void onStart(){
        super.onStart();
        mapView.onStart();
    }

    public void onStop(){
        super.onStop();
        mapView.onStart();
    }

    public void onPause(){
        super.onPause();
        mapView.onPause();
    }

    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }

    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
