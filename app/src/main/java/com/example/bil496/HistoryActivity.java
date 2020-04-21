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
import java.util.Queue;


public class HistoryActivity extends Activity {

    private ArrayList<String> list = new ArrayList<>();
    private String url = "https://restservices496.herokuapp.com/donates";
    private RequestQueue mQueue;
    private ArrayAdapter<String> veriAdaptoru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listemiz=(ListView) findViewById(R.id.listView1);
        Bundle gelenVeri = getIntent().getExtras();
        final String deger = gelenVeri.getString("container_id");
        System.out.println(deger);

        //(B) adımı
        veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, list);

        //(C) adımı
        listemiz.setAdapter(veriAdaptoru);

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
                            if(type.equals("dog")){
                        //if(container == Integer.parseInt(deger) && amount != null){
                                veriAdaptoru.add(fullName + "                " + amount + " kg" +
                                        "     " + liked);
                                veriAdaptoru.notifyDataSetChanged();
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
        veriAdaptoru.add("Sukran Saygili" + "            " + 36 + " Dolar" +
                "     " + 3);
        veriAdaptoru.add("Tolga ACAR" + "                " + 48 + " Dolar" +
                "     " + 0);
        veriAdaptoru.add("Tolga ACAR" + "                " + 12 + " Dolar" +
                "     " + 0);
        veriAdaptoru.add("Onur TETIK " + "                " + 36 + " Dolar" +
                "     " + 3);

    }
}

