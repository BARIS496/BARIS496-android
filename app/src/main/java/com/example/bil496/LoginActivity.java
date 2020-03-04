package com.example.bil496;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void createNewAccount(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent( this, ContainerActivity.class);
        startActivity(intent);
    }
    public void view(View view){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}