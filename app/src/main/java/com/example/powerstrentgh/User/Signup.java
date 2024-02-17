package com.example.powerstrentgh.User;

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
import androidx.core.content.ContextCompat;

import com.example.powerstrentgh.ModelCLass.CurrentStatusDetails;
import com.example.powerstrentgh.R;
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
    TextView tvlogin;
    Spinner spinner;
    String currentstatus;
    Button btnSignup;
    private FirebaseAuth mAuth;
    EditText edFname,edemail,edpass;
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
        arrayList.add("Trainner");
        arrayList.add("User");
//        arrayList.add("Admin");
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
    }
    private void Ids(){
        tvlogin=findViewById(R.id.tvsign);
        spinner = findViewById(R.id.spinner);
        btnSignup=findViewById(R.id.appCompatButton3);
        edFname=findViewById(R.id.name);
        edemail=findViewById(R.id.emailcreate);
        edpass=findViewById(R.id.pass);
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
                if(edFname.getText().toString().isEmpty()||
                        edpass.getText().toString().isEmpty()||
                        edemail.getText().toString().isEmpty()
                ){
                    Toast.makeText(Signup.this, "Enter Detail please", Toast.LENGTH_SHORT).show();

                }else if(edpass.getText().toString().length()<6){
                    Toast.makeText(Signup.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                }else if(!edemail.getText().toString().contains("@gmail.com")){
                    Toast.makeText(Signup.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }
                else{
                    dialog = new ProgressDialog(Signup.this);
                    dialog.setMessage("please wait...");
                    dialog.show();
                    Signup(edemail.getText().toString(),edpass.getText().toString());

                }
            }
        });

    }
    private void Signup(String email,String pass){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String uid = user.getUid();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("Status",currentstatus);
                    Toast.makeText(Signup.this, "welcome"+" "+currentstatus.toString(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    AddUSerDataToRealtime(uid,edFname.getText().toString(),
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
    private void AddUSerDataToRealtime(String userid,String name,String email,String pass){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Gym App").child("Account Details");
        CurrentStatusDetails obj=new CurrentStatusDetails(
                userid,
                currentstatus,
                name,
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
    public void onBackPressed() {
        // Add your code here to handle the back button press
        super.onBackPressed(); // This line closes the app by default
        finishAffinity();
    }
}