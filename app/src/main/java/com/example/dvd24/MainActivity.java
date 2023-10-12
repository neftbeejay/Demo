package com.example.dvd24;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.dvd24.API.CustomAdapter;
import com.example.dvd24.API.OnFetchDataListener;
import com.example.dvd24.API.RequestManager;
import com.example.dvd24.API.SelectListener;
import com.example.dvd24.DocBao.DocBao;
import com.example.dvd24.Models.NewApiResponse;
import com.example.dvd24.Models.NewHeadlines;
import com.example.dvd24.Nav_drawer.DrawerBaseActivity;
import com.example.dvd24.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerBaseActivity implements SelectListener, View.OnClickListener{
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    ImageSlider imageSlider;
    Button b1, b2, b3, b4,b5,b6,b7,b8;
    SearchView searchView;
    @NonNull
    ActivityMainBinding activityLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());

        Click();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news article of " + query);
                dialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener, "general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articles..");
        dialog.show();


        imageSlider =findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.dora_nobi,null));
        imageList.add(new SlideModel(R.drawable.doraemon_te,"Add Title here",null));
        imageList.add(new SlideModel(R.drawable.nobi_xuka,null));
        imageList.add(new SlideModel(R.drawable.nobi_xuka_l,null));
        imageList.add(new SlideModel(R.drawable.doremon_b,null));
        imageList.add(new SlideModel(R.drawable.doremon_cute,null));
        imageList.add(new SlideModel(R.drawable.dorra,null));
//        imageList.add(new SlideModel("https://www.google.com.vn/imgres?imgurl=https%3A%2F%2Fafamilycdn.com%2F150157425591193600%2F2023%2F5%2F17%2F5gsy4y3exa2b55a3-16842426875391913339926-1684301433552-168430143389113288671.jpg&tbnid=6ALgVnbQzNGzVM&vet=12ahUKEwijv7CxsqKAAxW1slYBHZAeBBYQMygdegUIARCIAg..i&imgrefurl=https%3A%2F%2Fafamily.vn%2Fmoi-dua-tre-bi-bat-nat-co-le-deu-mong-tro-thanh-nobita-co-cau-ban-doraemon-voi-tui-than-ky-cuu-vot-tuoi-tho-cua-minh-20230517123233417.chn&docid=07HvktQqwmEraM&w=1920&h=1080&q=doraemon&ved=2ahUKEwijv7CxsqKAAxW1slYBHZAeBBYQMygdegUIARCIAg.jpg",null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);


        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general",null);


        // click doc bao vn
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, DocBao.class);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, Video_new.class);
                startActivity(intent);
            }
        });
    }

    private void Click() {
        b1 = findViewById(R.id.btn_1);
        b2 = findViewById(R.id.btn_2);
        b3 = findViewById(R.id.btn_3);
        b4 = findViewById(R.id.btn_4);
        b5 = findViewById(R.id.btn_5);
        b6 = findViewById(R.id.btn_6);
        b7 = findViewById(R.id.btn_7);
        b8 = findViewById(R.id.btn_8);
        searchView = findViewById(R.id.search_view);
    }


    private final OnFetchDataListener<NewApiResponse> listener = new OnFetchDataListener<NewApiResponse>() {
        @Override
        public void onFetchData(List<NewHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this,"Djt me may doi load xiu !!",Toast.LENGTH_SHORT).show();
            }
            else {
                showNews(list);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {

            Toast.makeText(MainActivity.this,"An error Occured!!!",Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewHeadlines newHeadlines) {
   startActivity(new Intent(MainActivity.this, DetailsActivity.class)
           .putExtra("data",newHeadlines));
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        dialog.show();
        dialog.setTitle("Fetching news article of" + category);
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, category,null);
    }
}