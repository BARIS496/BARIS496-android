package com.example.bil496;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView mail;
        mail = findViewById(R.id.email);

        Button login, view, create;
        login = findViewById(R.id.login);
        view = findViewById(R.id.button2);
        create = findViewById(R.id.button3);

        /*if(pass.toString() == null || mail.toString() == null)
            Toast.makeText(getApplicationContext(), "Mail and password field cannot be empty", Toast.LENGTH_LONG).show();*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Butona tıklandığında ne yapmasını gerektiğini belirttik
                Intent gecisYap = new Intent(LoginActivity.this, ContainerActivity.class);
                startActivity(gecisYap);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(gecisYap);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(gecisYap);
            }
        });
    }


}