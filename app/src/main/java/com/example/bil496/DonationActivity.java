package com.example.bil496;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DonationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_donation);

        Button button1, button2, button3;
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        TextView text1, text2, text3, cartText;
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        cartText = findViewById(R.id.sepetOzeti);

        final int[] sayac = new int[3];

        Bundle gelenVeri=getIntent().getExtras();
        final int deger = gelenVeri.getInt("container_id");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayac[0]++;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayac[1]++;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayac[2]++;
            }
        });

        text1.append("by Simply Perfection\n" +
                "Super Premium Chicken and Brown Rice Recipe Dog Kibble\n" +
                "Size: 4 LB\n" +
                "Price: $10.25\n\n" +
                "Chicken Is First Ingredient\n" +
                "Completely Free Of Grain, Featuring No Wheat, Corn Or Soy\n" +
                "Protein Fiber For Healthy Digestion\n" +
                "Omega 3 And Omega 6 Fatty Acids Support Shiny Coats And Healthy Skin\n" +
                "No Artificial Flavors, Colors Or Preservatives");
        text3.append("by Purina Pro Plan\n" +
                "Dry Puppy Food \n" +
                "Size: 5 LB\n" +
                "Price:$38.48 \n" +
                "Made with high-quality protein, including real chicken as the first ingredient\n" +
                "DHA from omega-rich fish oil helps to nourish his brain and vision development\n" +
                "Antioxidant-rich formula helps support a puppy's developing immune system\n" +
                "100% complete and balanced nutrition for puppies, up to 1 year of age");
        text2.append("by Taste of the Wild\n" +
                "Taste of the Wild High Protein Real Meat Recipe Premium Dry Dog Food with Roasted Bison and Roasted Venison\n" +
                "Size: 5 LB\n" +
                "Price:\t$13.99\n" +
                "High protein ingredients with added vitamins & minerals; fruits and vegetables as SUPERFOODS for hard-working antioxidants; fatty acid blend for SKIN & coat\n" +
                "Ingredients from TRUSTED, SUSTAINABLE sources around the world; made with no grain, corn, wheat, filler; NO ARTIFICIAL flavors, colors, preservatives added\n" +
                "Nutrient rich and HIGHLY digestible with guaranteed species-specific, proprietary PROBIOTICS that survive and thrive in the GI tract\n" );

    }
}
