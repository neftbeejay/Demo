package com.example.dvd24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dvd24.Models.NewHeadlines;
import com.example.dvd24.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    NewHeadlines newHeadlines;
    TextView txt_title, author, time, detail, context;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        newHeadlines = (NewHeadlines) getIntent().getSerializableExtra("data");
        txt_title = findViewById(R.id.text_details_title);
        author    = findViewById(R.id.text_detail_author);
        time      = findViewById(R.id.text_detail_time);
        detail    = findViewById(R.id.text_detail_detail);
        context   = findViewById(R.id.text_detail_content);
        imageView = findViewById(R.id.img_detail_news);

        txt_title.setText(newHeadlines.getTitle());
        author.setText(newHeadlines.getAuthor());
        time.setText(newHeadlines.getPublishedAt());
        detail.setText(newHeadlines.getDescription());
        context.setText(newHeadlines.getContent());
        Picasso.get().load(newHeadlines.getUrlToImage()).into(imageView);

    }


}