package com.example.bil496;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonationActivity extends AppCompatActivity {

    private RecyclerView recyclerViewKategori;
    private ArrayList<ProductActivity> food_list;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_donation_helper);

        recyclerViewKategori = (RecyclerView)findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recyclerViewKategori.setLayoutManager(layoutManager);

        food_list = new ArrayList<>();
        food_list.add(new ProductActivity(R.drawable.kopek_mama3,"by Simply Perfection\n" +
                "Super Premium Chicken and Brown Rice Recipe Dog Kibble\n" +
                "Size: 4 LB", 10.25));
        food_list.add(new ProductActivity(R.drawable.kopek_mama2,"by Purina Pro Plan\n" +
                "Dry Puppy Food \n" +
                "Size: 5 LB",13.99));
        food_list.add(new ProductActivity(R.drawable.kopek_mama,"by Taste of the Wild\n" +
                "Taste of the Wild High Protein Real Meat Recipe Premium Dry Dog Food with Roasted Bison and Roasted Venison\n" +
                "Size: 5 LB",38.48));

        GridViewAdapter adapter_items = new GridViewAdapter(this,food_list);
        recyclerViewKategori.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewKategori.setAdapter(adapter_items);
    }
}
