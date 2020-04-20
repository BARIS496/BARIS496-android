package com.example.bil496;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.HttpHeaderParser;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;
public class CartActivity extends AppCompatActivity {

    private Button executeBtn;
    private EditText foodName, foodType, fullName, cardNumber, exDate, ccv, amount;
    private String url = "https://restservices496.herokuapp.com/addDonate";
    private RequestQueue mQueue;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cart);

        executeBtn = (Button)findViewById(R.id.button);
        foodName = (EditText)findViewById(R.id.foodName);
        foodType = (EditText)findViewById(R.id.foodType);
        fullName = (EditText)findViewById(R.id.fullName);
        cardNumber = (EditText)findViewById(R.id.cardNumber);
        exDate = (EditText)findViewById(R.id.expDate);
        ccv = (EditText)findViewById(R.id.ccvNumber);
        amount = (EditText)findViewById(R.id.amount);

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        executeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringRequest jsonForPostRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d("snow", response.toString());
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    //Log.d("snowP", "onResponse: " + jsonObject.getString("job"));
                                    //Log.d("snowP", "onResponse: " + jsonObject.getString("createdAt"));
                                } catch (Exception e) {
                                    Log.d("snowP", "hata: " );
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("fullName","aaa");
                        params.put("creditCardNumberStr","100");
                        params.put("donaterMail","2");
                        params.put("expiration_dateStr", "0");
                        params.put("cvvNumberStr","cat");
                        params.put("promotionCode","0");
                        params.put("discountCode","123");
                        params.put("containerId","0");
                        System.out.println(params);
                        return params;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset:utf-8";
                    }
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/json; charset:utf-8");
                        return params;
                    }

                };
                queue.add(jsonForPostRequest);
            }
        });
    }

}
