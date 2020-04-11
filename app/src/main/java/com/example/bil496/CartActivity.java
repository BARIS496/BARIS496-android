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

        mQueue = Volley.newRequestQueue(this);
        executeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest jsonForPostRequest = new StringRequest(
                        Request.Method.POST,url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.i("log",response.toString());

                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(),""+jsonObject.getString(String.valueOf(Integer.parseInt("mesaj"))),Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        finish();
                        NetworkResponse response = error.networkResponse;
                        if(response != null && response.data != null){
                            JSONObject jsonObject = null;
                            String errorMessage = null;

                            switch(response.statusCode){
                                case 400:
                                    errorMessage = new String(response.data);

                                    try {
                                        jsonObject = new JSONObject(errorMessage);
                                        String serverResponseMessage = (String)jsonObject.get("hataMesaj");;
                                        Toast.makeText(getApplicationContext(),""+serverResponseMessage,Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                            }
                        }
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("foodType","aaa");
                        params.put("amountStr","100");
                        params.put("likedStr","2");
                        params.put("containerId", "0");
                        params.put("donateType","cat");
                        params.put("promotionCode","0");
                        params.put("creditCardNumberStr","123");
                        params.put("fullName","SUKO");
                        params.put("expiration_dateStr","123");
                        params.put("cvvNumberStr","123");
                        params.put("recieverName","ali");
                        params.put("donateFoodName"," ttt");
                        params.put("donaterMail","gsukransaygili@gmail.com");
                        params.put("discountCode","0");
                        params.put("donateTime","2020-04-08 20:53:35");

                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("Content-Type", "application/json; charset=utf-8");
                        return param;
                    }
                };
                jsonForPostRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                mQueue.add(jsonForPostRequest);
            }
        });
    }

}
