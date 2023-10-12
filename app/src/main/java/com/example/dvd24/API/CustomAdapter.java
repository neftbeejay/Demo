package com.example.dvd24.API;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dvd24.Models.NewHeadlines;
import com.example.dvd24.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomView> {
   private Context context;
   private List<NewHeadlines> newHeadlines;
   private SelectListener listener;

    public CustomAdapter(Context context, List<NewHeadlines> newHeadlines, SelectListener listener) {
        this.context = context;
        this.newHeadlines = newHeadlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomView(LayoutInflater.from(context).inflate(R.layout.headline_list_items,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, @SuppressLint("RecyclerView") int position) {

        holder.text_title.setText(newHeadlines.get(position).getTitle());
        holder.text_source.setText(newHeadlines.get(position).getSource().getName());

       if(newHeadlines.get(position).getUrlToImage()!=null)
       {
           Picasso.get().load(newHeadlines.get(position).getUrlToImage()).into(holder.img_headline);
       }
       holder.main_container.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.OnNewsClicked(newHeadlines.get(position));
           }
       });

    }

    @Override
    public int getItemCount() {
        return newHeadlines.size();
    }
}
