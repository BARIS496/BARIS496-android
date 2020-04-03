package com.example.bil496;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*Recyclerview icerisindeki nesnelerin tanımlanması ve sayfa gecislerinin
  tanimlandigi siniftir.
 */

public class GridViewHolder extends RecyclerView.ViewHolder {
    Context context = null;
    public ImageView img;
    public TextView textBaslik;
    public TextView textFiyat;
    public Button addButton;

    public GridViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        img = itemView.findViewById(R.id.imageView);
        textBaslik = itemView.findViewById(R.id.urunOzelligi);
        textFiyat = itemView.findViewById(R.id.fiyat);
        addButton = itemView.findViewById(R.id.button);


    }
}
