package com.example.epubfoliotest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class epubAdapter extends RecyclerView.Adapter<epubViewHolder> {
    private Context context;
    private List<File> epubFiles;
    private onepubFileSelectListener listener;

    public epubAdapter(Context context, List<File> epubFiles, onepubFileSelectListener listener) {
        this.context = context;
        this.epubFiles = epubFiles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public epubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new epubViewHolder(LayoutInflater.from(context).inflate(R.layout.element_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull epubViewHolder epubViewHolder, @SuppressLint("RecyclerView") final int position) {
        //public void onBindViewHolder(@NonNull epubViewHolder epubViewHolder, final int position) {
        epubViewHolder.tvName.setText(epubFiles.get(position).getName());
        epubViewHolder.tvName.setSelected(true);

        epubViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onepubSelected(epubFiles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return epubFiles.size();
    }
}
