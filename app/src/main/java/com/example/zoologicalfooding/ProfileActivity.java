package com.example.zoologicalfooding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    /**
     * Created by SukranSaygili on 02/02/2020.
     */

    TextView first_name, last_name, textViewEmail, address, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefmanager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        first_name = (TextView) findViewById(R.id.fName);
        last_name = (TextView) findViewById(R.id.lName);
        textViewEmail = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);
        pass = (TextView) findViewById(R.id.password);


        //getting the current user
        User user = SharedPrefmanager.getInstance(this).getUser();

        //setting the values to the textviews
        first_name.setText(String.valueOf(user.getFname()));
        last_name.setText(user.getLname());
        textViewEmail.setText(user.getEmail());
        address.setText(user.getAddress());
        pass.setText(user.getPassword());

        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefmanager.getInstance(getApplicationContext()).logout();
            }
        });
    }
}
