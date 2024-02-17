package com.example.powerstrentgh.User;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.powerstrentgh.R;

public class ChallengeStartActivity extends AppCompatActivity {
    private VideoView videoView;
   String videoPath;
   TextView textView;

   Button btn;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_challenge_start);
        videoView = (VideoView) findViewById(R.id.videoView);
        btn =findViewById(R.id.endID);
        textView=findViewById(R.id.exname);
        textView.setText(getIntent().getStringExtra("Username"));
        String getvideoname=getIntent().getStringExtra("Username");
        Toast.makeText(this, ""+getvideoname, Toast.LENGTH_SHORT).show();
        if (getvideoname.equals("dumbbellcurl")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.dumbbellcurl;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }else if (getvideoname.equals("hammercurls")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.hammercurls;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("rodcurl")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.rodcurl;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("reardelt")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.reardelt;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("dumbbelfront")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.dumbbelfront;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("shoulderpress")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.shoulderpress;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("backsquats")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.backsquats;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("sumosquats")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.sumosquats;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("inclinebenchpress")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.inclinebenchpress;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("inclinedumbbellpress")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.inclinedumbbellpress;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        else if (getvideoname.equals("cablechestcross")){
            getWindow().setFormat(PixelFormat.UNKNOWN);
            videoPath = "android.resource://" + getPackageName() + "/" +R.raw.cablechestcross;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
            }
        });
    }

}