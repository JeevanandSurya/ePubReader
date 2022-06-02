package com.example.epubfoliotest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class epubViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public CardView container;

    public epubViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.textepubName);
        container = itemView.findViewById(R.id.container);
    }
}
