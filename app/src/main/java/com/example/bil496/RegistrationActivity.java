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

import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.util.concurrent.ExecutionException;


public class RegistrationActivity extends Activity {
    Button buton;
    EditText name, lastName, address, email, pass;
    ProgressDialog pDialog;
    public URL url;

    HttpURLConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.foodName);
        lastName = (EditText) findViewById(R.id.foodType);
        address = (EditText) findViewById(R.id.amount);
        email = (EditText) findViewById(R.id.fullName);
        pass = (EditText) findViewById(R.id.cardNumber);

        buton = (Button) findViewById(R.id.login);
        buton.setOnClickListener(new View.OnClickListener() {
            //buton a click listener ekledik

            public void onClick(View v) {
                SendPost async = new SendPost();
                try {
                    async.execute().get();
                    finish();
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

            //{"first_name":"dnm1","last_name":"dnm2","address":"dnm3","email":"dnm4@gmail.com","pass":"dnm5"}
            try {
                jsonEmailData.put("first_name", name.getText().toString());
                jsonEmailData.put("last_name", lastName.getText().toString());
                jsonEmailData.put("address", address.getText().toString());
                jsonEmailData.put("email", email.getText().toString());
                jsonEmailData.put("pass", pass.getText().toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                url = new URL("http://restservices496.herokuapp.com/addMember");
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
                    System.out.println(sb.toString());
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

