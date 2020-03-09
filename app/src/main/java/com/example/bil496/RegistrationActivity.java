package com.example.bil496;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;


public class RegistrationActivity extends AppCompatActivity {

    private EditText firstName, lastName, address, email, username, password;
    private String firstName2, lastName2, address2, email2, username2, password2;
    private RequestQueue mQueue;
    private String url;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.first_name);
        firstName2 = String.valueOf(firstName);
        lastName = findViewById(R.id.last_name);
        lastName2 =String.valueOf(lastName);
        address = findViewById(R.id.address);
        address2 = String.valueOf(address);
        email = findViewById(R.id.email);
        email2 = String.valueOf(email);
        username = findViewById(R.id.username);
        username2 = String.valueOf(username);
        password = findViewById(R.id.password);
        password2 = String.valueOf(password);


        mQueue = Volley.newRequestQueue(this);
        jsonPost(firstName2,lastName2,address2,email2,username2,password2);

    }

    private void jsonPost(final String firstName, final String lastName, final String address, final String email, final String password, String response){
        url = "http://restservices496.herokuapp.com/addMember";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                // response
                Log.d("Response", string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Veriler Okunurken Hata Oluştu", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> member = new HashMap<>();
                member.put("first_name", firstName);
                member.put("last_name", lastName);
                member.put("address", address);
                member.put("email",email);
                member.put("username", String.valueOf(username));
                member.put("password",password);
                return member;
            }
        };

        mQueue.add(request);

    }
}
