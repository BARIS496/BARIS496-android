package com.example.zoologicalfooding;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtxt;

    private MySingleton(Context context){
        mCtxt = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mCtxt);

        return requestQueue;

    }

    public static synchronized MySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }

        return mInstance;
    }

    public <T> void addRequestQueue(Request<T> request){
        requestQueue.add(request);
    }


}
