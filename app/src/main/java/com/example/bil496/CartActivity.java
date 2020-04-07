package com.example.bil496;
import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

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

        Bundle gelenVeri = getIntent().getExtras();
        final String deger = gelenVeri.getString("container_id");

        mQueue = Volley.newRequestQueue(this);
        jsonPost(foodName.toString(),foodType.toString(),fullName.toString(),cardNumber.toString(),
                exDate.toString(),ccv.toString(),amount.toString(),deger);

    }

    private void jsonPost(final String foodName, final String foodType, final String fullName,
                          final String cardNumber, final String exDate, final String ccv, String amount, final String deger){
        url = "http://restservices496.herokuapp.com/addDonate";

        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                // response
                Log.d("Response", string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Veriler Okunurken Hata Oluştu" + deger, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> donateTable_generator = new HashMap<>();
                donateTable_generator.put("containerId", deger);
                donateTable_generator.put("donateType", "kopek");
                donateTable_generator.put("creditCardNumber", String.valueOf(cardNumber));
                donateTable_generator.put("fullName", String.valueOf(fullName));
                donateTable_generator.put("expiration_date",String.valueOf(exDate));
                donateTable_generator.put("cvvNumber", String.valueOf(ccv));
                return donateTable_generator;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        executeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Butona tıklandığında ne yapmasını gerektiğini belirttik
                mQueue.add(request);
            }
        });
    }
}
