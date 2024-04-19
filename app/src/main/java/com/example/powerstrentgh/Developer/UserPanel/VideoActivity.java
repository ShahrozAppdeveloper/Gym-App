package com.example.powerstrentgh.Developer.UserPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.powerstrentgh.R;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView videoView = findViewById(R.id.videoView);
        Intent intent = getIntent();
         String name = intent.getStringExtra("videoname");
         if (name.equals("biceps")){
// Get the URI of the video file from the raw folder
             String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.biceps;
             // Set the video URI to VideoView
             videoView.setVideoURI(Uri.parse(videoPath));
         } else if (name.equals("back")) {
             String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.back;
             // Set the video URI to VideoView
             videoView.setVideoURI(Uri.parse(videoPath));
         }else if (name.equals("chest")) {
             String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.chest;
             // Set the video URI to VideoView
             videoView.setVideoURI(Uri.parse(videoPath));
         }else if (name.equals("arms")) {
             String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.arms;
             // Set the video URI to VideoView
             videoView.setVideoURI(Uri.parse(videoPath));
         }else if (name.equals("legs")) {
             String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.legs;
             // Set the video URI to VideoView
             videoView.setVideoURI(Uri.parse(videoPath));
         }else if (name.equals("shoulders")) {
             String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.shoulders;
             // Set the video URI to VideoView
             videoView.setVideoURI(Uri.parse(videoPath));
         }else {
             Toast.makeText(this, "Video not available", Toast.LENGTH_SHORT).show();
         }

        videoView.requestFocus();
        // Start playing the video
        videoView.start();
    }
}