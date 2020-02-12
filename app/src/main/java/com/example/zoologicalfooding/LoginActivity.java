package com.example.zoologicalfooding;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button bLogin;
    Button bCreate;
    TextView email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = (Button) findViewById(R.id.login);
        bCreate = (Button) findViewById(R.id.create);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Butona tıklandığında ne yapmasını gerektiğini belirttik
                if(!(email.getText().toString().isEmpty())){
                    Intent gecisYap = new Intent(LoginActivity.this, MainActivity.class);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(gecisYap);
                }
                else
                    Toast.makeText(LoginActivity.this, "Incorrect Details", Toast.LENGTH_LONG).show();

            }
        });

        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Butona tıklandığında ne yapmasını gerektiğini belirttik
                Intent gecisYap = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(gecisYap);
            }
        });
    }
}
