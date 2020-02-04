package com.example.zoologicalfooding;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SukranSaygili on 01/02/2020.
 */

public class RegisterActivity extends AppCompatActivity {

    EditText fName, lName, editTextEmail, editTextPassword, editTextAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefmanager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }


        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextAddress = (EditText) findViewById(R.id.address);


        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on login
                //we will open the login screen
                finish();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void registerUser() {
        final String username = fName.getText().toString().trim();
        final String lastname = lName.getText().toString().trim();
        final String address = editTextAddress.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //first we will do the validations

        if (TextUtils.isEmpty(username) ) {
            fName.setError("Please enter username");
            fName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(lastname) ) {
            fName.setError("Please enter lastname");
            lName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("members");

                                //creating a new user object
                                User user = new User(
                                        userJson.getString("first_name"),
                                        userJson.getString("last_name"),
                                        userJson.getString("address"),
                                        userJson.getString("email"),
                                        userJson.getString("password")
                                );

                                //storing the user in shared preferences
                                SharedPrefmanager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", username);
                params.put("second_name", lastname);
                params.put("address", address);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

}