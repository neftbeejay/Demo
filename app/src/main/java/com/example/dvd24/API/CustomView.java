package com.example.dvd24.API;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dvd24.R;

public class CustomView extends RecyclerView.ViewHolder {

    TextView text_title, text_source;
    ImageView img_headline;
    CardView main_container;
    public CustomView(@NonNull View itemView) {
        super(itemView);

        anhxa();
    }

    private void anhxa() {
        text_title    = itemView.findViewById(R.id.text_title);
        text_source   = itemView.findViewById(R.id.text_source);
        img_headline  = itemView.findViewById(R.id.img_headline);
        main_container= itemView.findViewById(R.id.main_container);
    }
}
