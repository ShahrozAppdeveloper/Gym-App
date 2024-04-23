package com.example.powerstrentgh.Developer.TrainerPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.ActivityCreateMealForUserBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateMealForUserActivity extends AppCompatActivity {
   private ActivityCreateMealForUserBinding binding;
   DatabaseReference reference;
   FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateMealForUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent obj = getIntent();
        String userID = obj.getStringExtra("userId");
    }
    private void UploadMealForUser(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("MealDetails");

    }
}