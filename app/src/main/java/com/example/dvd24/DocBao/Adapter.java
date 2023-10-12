package com.example.dvd24.DocBao;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dvd24.DangBao.DataClass;
import com.example.dvd24.DangBao.DetailUpBao;
import com.example.dvd24.R;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context contextb;
    private List<DataClass> dataListb;

    public Adapter(Context context, List<DataClass> dataList) {
        this.contextb = context;
        this.dataListb = dataList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_up, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(contextb).load(dataListb.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataListb.get(position).getDataTitle());
        holder.recDesc.setText(dataListb.get(position).getDataDesc());
        holder.recLang.setText(dataListb.get(position).getDataLang());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contextb, DetailDocBao.class);
                intent.putExtra("Image", dataListb.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description", dataListb.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Title", dataListb.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Language", dataListb.get(holder.getAdapterPosition()).getDataLang());


                contextb.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataListb.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataListb = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle, recDesc, recLang;
    CardView recCard;
    public MyViewHolder(@NonNull View view){
        super(view);


        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
    }
}
