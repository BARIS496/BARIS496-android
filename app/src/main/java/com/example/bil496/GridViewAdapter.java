package com.example.bil496;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewHolder> {
    private ArrayList<ProductActivity> kategoriler;
    private Context context;
    private LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, @NonNull ArrayList<ProductActivity> kategoriler) {
        this.kategoriler = kategoriler;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //satir goruntusu burada olusturulur

        View satir = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_donation,null);
        GridViewHolder holder = new GridViewHolder(satir);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, final int position) {
        //imageView, textView degerleri burada doldurulur
        Glide.with(context).load(kategoriler.get(position).getImageId()).into(holder.img);
        holder.textBaslik.setText(kategoriler.get(position).getName());
        holder.textFiyat.setText((String.valueOf(kategoriler.get(position).getPrice())));

        ContainerDetailsActivity activity = new ContainerDetailsActivity();
        final int sayi = activity.getCont();

        //fotografa basildiginda detail sayfasina burdan gecilir
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //satira tiklama burada yapilir

            @Override
            public void onClick(View view) {
                Intent gecisYap = new Intent(context, CartActivity.class);
                gecisYap.putExtra("kategori_id",kategoriler.get(position).getImageId());
                gecisYap.putExtra("container_id",String.valueOf(sayi));
                context.startActivity(gecisYap);
            }
        });
    }
    public long getItemId(int position){
        return kategoriler.get(position).getImageId();
    }

    @Override
    public int getItemCount() {
        return kategoriler.size();
    }
}
