package com.example.powerstrentgh.User;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.powerstrentgh.Activity.ForgotpasswordActivity;
import com.example.powerstrentgh.Admin.AdminDashboard;
import com.example.powerstrentgh.Developer.TrainerPanel.TrainerCreateProfileActivity;
import com.example.powerstrentgh.Developer.UserPanel.UserCreateProfileActivity;
import com.example.powerstrentgh.ModelCLass.CurrentStatusDetails;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.SharedPrefPkg.PrefManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Signup extends AppCompatActivity {
    TextView tvlogin, forgetPassword;
    Spinner spinner;
    String currentstatus;
    CardView btnSignup;
    private FirebaseAuth mAuth;
    EditText edemail,edpass;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.firstcolor));
        }
        // calling method
        Ids();
        clicklistener();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Trainer");
        arrayList.add("User");
        arrayList.add("Admin");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentstatus = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected:"+currentstatus,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, ForgotpasswordActivity.class));
            }
        });

    }
    private void Ids(){
        tvlogin=findViewById(R.id.tvsign);
        spinner = findViewById(R.id.spinner);
        btnSignup=findViewById(R.id.appCompatButton3);
        edemail=findViewById(R.id.emailid);
        edpass=findViewById(R.id.passwordID);
        forgetPassword = findViewById(R.id.forgetPasswordTextView);
    }
    private void clicklistener(){
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edpass.getText().toString().trim().isEmpty()||
                        edemail.getText().toString().trim().isEmpty()
                ){
                    Toast.makeText(Signup.this, "Enter Detail please", Toast.LENGTH_SHORT).show();

                }else if(edpass.getText().toString().trim().length()<6){
                    Toast.makeText(Signup.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                }else if(!edemail.getText().toString().trim().contains("@gmail.com")){
                    Toast.makeText(Signup.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }
                else{
                    dialog = new ProgressDialog(Signup.this);
                    dialog.setMessage("please wait...");
                    dialog.show();
                    Signup(edemail.getText().toString().trim(),edpass.getText().toString().trim(), currentstatus);

                }
            }
        });

    }
    private void Signup(String email,String pass, String currentRole){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String uid = user.getUid();
                    Intent obj =new Intent(getApplicationContext(), MainActivity.class);
                    obj.putExtra("status",currentstatus);
                    startActivity(obj);


                    AddUSerDataToRealtime(uid,
                            edemail.getText().toString(),
                            edpass.getText().toString());
                    dialog.dismiss();
                }
                else{
                    dialog.hide();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AddUSerDataToRealtime(String userid,String email,String pass){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Gym App").child("Account Details");
        CurrentStatusDetails obj=new CurrentStatusDetails(
                userid,
                currentstatus,
                email,
                pass);
        myRef.child(userid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Signup.this, "Sucesfully Updated", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        PrefManager prefManager1= new PrefManager(getApplicationContext());
        if (prefManager1.getCurrentstatus().equals("Trainer")){
            //step 1.
            Intent intent=new Intent(getApplicationContext(), TrainerCreateProfileActivity.class);
            startActivity(intent);
        }else if(prefManager1.getCurrentstatus().equals("User")) {
            Intent intent = new Intent(getApplicationContext(), UserCreateProfileActivity.class);
            startActivity(intent);
        }
        else if(prefManager1.getCurrentstatus().equals("Admin")) {
            Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
            startActivity(intent);
        }
        // step 2
//        }else if (prefManager1.getCurrentstatus().equals("Admin")){
//            //step 2
//            Intent intent=new Intent(getApplicationContext(), AdminDashboard.class);
//            startActivity(intent);
//        }
    }
    @Override
    public void onBackPressed() {
        // Add your code here to handle the back button press
        super.onBackPressed(); // This line closes the app by default
        finishAffinity();
    }
}