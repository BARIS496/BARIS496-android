package com.example.bil496;
import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.util.concurrent.ExecutionException;
import android.widget.EditText;

public class CartActivity extends AppCompatActivity {

    private Button executeBtn;
    private EditText foodName, foodType, fullName, cardNumber, exDate, ccv, amount, email, promotion;
    private String url = "https://restservices496.herokuapp.com/addDonate";
    String deger;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cart);

        Bundle gelenVeri = getIntent().getExtras();
        deger = gelenVeri.getString("container_id");

        executeBtn = (Button)findViewById(R.id.button);
        foodName = (EditText)findViewById(R.id.foodName);
        foodType = (EditText)findViewById(R.id.foodType);
        fullName = (EditText)findViewById(R.id.fullName);
        cardNumber = (EditText)findViewById(R.id.cardNumber);
        exDate = (EditText)findViewById(R.id.expDate);
        ccv = (EditText)findViewById(R.id.ccvNumber);
        amount = (EditText)findViewById(R.id.amount);
        email = (EditText) findViewById(R.id.email);
        promotion = (EditText) findViewById(R.id.promotion);

        executeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendPost async = new SendPost();
                try {
                    async.execute().get();
                } catch (ExecutionException e) {

                    e.printStackTrace();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

        });
    }
    class SendPost extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection;
            OutputStreamWriter request = null;
            URL url = null;
            String response = null;
            JSONObject jsonEmailData = new JSONObject();


            try {
                jsonEmailData.put("foodType", foodType.getText().toString());
                jsonEmailData.put("amountStr", amount.getText().toString());
                jsonEmailData.put("fullName", fullName.getText().toString());
                jsonEmailData.put("creditCardNumberStr", cardNumber.getText().toString());
                jsonEmailData.put("donaterMail", email.getText().toString());
                jsonEmailData.put("expiration_dateStr", exDate.getText().toString());
                jsonEmailData.put("cvvNumberStr",ccv.getText().toString());
                jsonEmailData.put("discountCode",promotion.getText().toString());
                jsonEmailData.put("containerId",deger);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                url = new URL("https://restservices496.herokuapp.com/addDonate");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("POST");
                request = new OutputStreamWriter(connection.getOutputStream());
                request.write(String.valueOf(jsonEmailData));
                request.flush();
                request.close();
                String line = "";
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                    //System.out.println(sb.toString());
                }
                //Response from server after recovery process will be stored in response variable.
                response = sb.toString().trim();
                JSONObject jObj = new JSONObject(response);
                final String status = jObj.getString("status");
                final String message = jObj.getString("message");
                Log.i("status:", status);
                Log.i("message:", message);
                isr.close();
                reader.close();
                return status;

            } catch (Exception e) {
                // Error
                e.printStackTrace();
                return "Fail sending";
            }
        }
    }
}
