package com.example.bil496;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

class DonationActivity extends AppCompatActivity {
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_donation);

        Button addCard, addFills;
        addCard = findViewById(R.id.button);
        addFills = findViewById(R.id.addedCard);

        EditText foodType, miktar,kartNo;
        foodType = findViewById(R.id.foodType);
        miktar = findViewById(R.id.miktar);
        kartNo = findViewById(R.id.kartNo);

        Bundle gelenVeri=getIntent().getExtras();
        final int deger = gelenVeri.getInt("container_id");


    }
}
