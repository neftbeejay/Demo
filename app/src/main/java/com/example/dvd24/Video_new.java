package com.example.dvd24;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import android.widget.ProgressBar;
import android.widget.VideoView;

public class Video_new extends AppCompatActivity {
    ProgressBar progressBar, progressBar2,progressBar3,progressBar4;
    VideoView videoView,videoView2,videoView4,videoView3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_new);

        anhxa();


        String videoPath = "android.resource://" + getPackageName() + "/"+ R.raw.videotho7mau;
        String videoPath2 = "android.resource://" + getPackageName() + "/"+ R.raw.tho7mau;
        String videoPath3 = "android.resource://" + getPackageName() + "/"+ R.raw.tho7mau;



        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        Uri uri2 = Uri.parse(videoPath2);
        videoView2.setVideoURI(uri2);

        Uri uri3 = Uri.parse(videoPath3);
        videoView3.setVideoURI(uri3);



//////////////////////////////

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
                progressBar.setVisibility(View.GONE);
            }
        });

        /////////////////////////////////////////////
        MediaController mediaController2 = new MediaController(this);
        videoView2.setMediaController(mediaController2);
        mediaController2.setAnchorView(videoView2);

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressBar2.setVisibility(View.GONE);
            }
        });

        MediaController mediaController3 = new MediaController(this);
        videoView3.setMediaController(mediaController3);
        mediaController3.setAnchorView(videoView3);

        videoView3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressBar3.setVisibility(View.GONE);
            }
        });



    }

    private void anhxa() {
         videoView = findViewById(R.id.video_view);
         videoView2 = findViewById(R.id.video_view2);
        videoView3 = findViewById(R.id.video_view3);
        progressBar = findViewById(R.id.pro);
        progressBar2 = findViewById(R.id.pro2);
        progressBar3 = findViewById(R.id.pro3);

    }


}