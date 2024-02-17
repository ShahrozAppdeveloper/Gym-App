package com.example.powerstrentgh.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.powerstrentgh.R;
import com.example.powerstrentgh.User.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends AppCompatActivity {

    Button Forgot;
    EditText foremail;
    String emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        Forgot=findViewById(R.id.reset);
        foremail=findViewById(R.id.forgotpass);
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emails=foremail.getText().toString().trim();
                if(emails.isEmpty()){
                    Toast.makeText(ForgotpasswordActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                else {
                    forgotpass();
                }


            }
        });
    }

    private void forgotpass() {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(emails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotpasswordActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ForgotpasswordActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(ForgotpasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    }
