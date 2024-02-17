package com.example.powerstrentgh.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.powerstrentgh.R;

public class Front extends AppCompatActivity {
    Button btnsigup,btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        btnsigup=findViewById(R.id.appCompatButton2signUp);
        btnSignIn=findViewById(R.id.appCompatButtonSigIN);
        btnsigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Front.this, Signup.class));
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Front.this, MainActivity.class));
            }
        });
    }
}