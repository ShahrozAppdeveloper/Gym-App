package com.example.powerstrentgh.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.powerstrentgh.Activity.ForgotpasswordActivity;
import com.example.powerstrentgh.ModelCLass.CurrentStatusDetails;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.SharedPrefPkg.PrefManager;
import com.example.powerstrentgh.Trainner.TrainnerProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String Currentuser;
    EditText edemail,edpass;
    TextView tvforgotpass,tvsignup;
    CardView btnlogin;
    ProgressDialog dialog;
    DatabaseReference databaseReference;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get value current value
        Intent intent = getIntent();
        Currentuser = intent.getStringExtra("Status");
        prefManager=new PrefManager(this);
        // calling method()
        Ids();
        clickListener();
        databaseReference= FirebaseDatabase.getInstance().getReference("Gym App").child("Account Details");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.firstcolor));
        }
    }
    private void Ids(){
//        spinner = findViewById(R.id.spinner);
        edemail=findViewById(R.id.emailid);
        edpass=findViewById(R.id.passwordID);
//        tvforgotpass=findViewById(R.id.textView3);
        btnlogin=findViewById(R.id.button);
        tvsignup=findViewById(R.id.textView6);
    }
    private void clickListener(){
//        tvforgotpass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ForgotpasswordActivity.class));
//            }
//        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edemail.getText().toString().isEmpty() ||
                        edpass.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Email and password", Toast.LENGTH_SHORT).show();
                }else if(!edemail.getText().toString().contains("@gmail.com")){
                    Toast.makeText(MainActivity.this, "Please Enter valid Email", Toast.LENGTH_SHORT).show();
                }else if(edpass.getText().toString().length()<6){
                    Toast.makeText(MainActivity.this, "Please Enter valid Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setMessage("please wait...");
                    dialog.show();
                    SignIn(edemail.getText().toString(),edpass.getText().toString());
                }
            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });
    }
    private void SignIn(String email,String pass){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                String current_user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId=currentUser.getUid();
                if (task.isSuccessful()){
                    System.out.println("userss__________________________"+currentUser);
                    databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            CurrentStatusDetails currentStatusDetails= snapshot.getValue(CurrentStatusDetails.class);
                            System.out.println("user status______________________"+currentStatusDetails.getCurentstatus());
                            if (currentStatusDetails.getCurentstatus().equals("Trainner")){
                                prefManager.setCurrentstatus("Trainner");
                                prefManager.setUserID(userId);
                                Intent intent=new Intent(getApplicationContext(),TrainnerProfileActivity.class);
                                startActivity(intent);
                                //next proccess
                            }else if (currentStatusDetails.getCurentstatus().equals("User")) {
                                prefManager.setCurrentstatus("User");
                                prefManager.setUserID(userId);
                                Intent intent = new Intent(getApplicationContext(), GoalActivity.class);
                                startActivity(intent);
                                // next 2nd step
                            }
//                            }else if (currentStatusDetails.getCurentstatus().equals("Admin")){
//                                prefManager.setCurrentstatus("Admin");
//                                prefManager.setUserID(currentUser.toString());
//                                Intent intent=new Intent(getApplicationContext(),AdminDashboard.class);
//                                startActivity(intent);
//
//                                // 3rd step
//                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            System.out.println("error________________________"+error.getMessage());
                        }
                    });

//                    Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
//                    intent.putExtra("Status",Currentuser);
//                    Toast.makeText(SigninActivity.this, "welcome"+Currentuser, Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//                    dialog.dismiss();
                }
                else{


                    dialog.hide();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        PrefManager prefManager1= new PrefManager(getApplicationContext());
        if (prefManager1.getCurrentstatus().equals("Trainner")){
            //step 1.
            Intent intent=new Intent(getApplicationContext(), TrainnerProfileActivity.class);
            startActivity(intent);
        }else if(prefManager1.getCurrentstatus().equals("User")) {
            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
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