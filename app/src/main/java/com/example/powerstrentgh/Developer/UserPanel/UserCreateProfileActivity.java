package com.example.powerstrentgh.Developer.UserPanel;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.TrainerPanel.TrainerCreateProfileActivity;
import com.example.powerstrentgh.Developer.TrainerPanel.TrainnerDashboardActivity;
import com.example.powerstrentgh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UserCreateProfileActivity extends AppCompatActivity {
    ImageView userImg;
    EditText userName;
    CardView createProfileButton;
    private Uri imageUri;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    FirebaseAuth mAuth;
    String currentuser,name;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_create_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userImg = findViewById(R.id.userCreateProfileImg);
        userName = findViewById(R.id.usercreateprofilenameid);
        createProfileButton = findViewById(R.id.createuserprofilebuttonid);

        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                imagePickerActivityResult.launch(galleryIntent);
            }
        });
        createProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = userName.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(UserCreateProfileActivity.this, "Please Enter name ", Toast.LENGTH_SHORT).show();
                }else{
                    UploadImageNdDetails();
                    dialog = new ProgressDialog(UserCreateProfileActivity.this);
                    dialog.setMessage("please wait...");
                    dialog.show();
                }
            }
        });
        // on back pressed
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

    }
    ActivityResultLauncher<Intent> imagePickerActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        imageUri = result.getData().getData();
                        userImg.setImageURI(imageUri);
                    }
                }
            }
    );
    private void UploadImageNdDetails()
    {
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("GymUser").child("Details");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        currentuser = user.getUid();

        if (imageUri != null) {
            StorageReference ref = storageReference.child("images/"
                    + UUID.randomUUID().toString());
            ref.putFile(imageUri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageurl=uri.toString();
                                            AdduserDetailsToDatabase obj=new AdduserDetailsToDatabase(currentuser,name,imageurl);
                                            databaseReference.child(currentuser).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        dialog.dismiss();
                                                        Intent intent=new Intent(UserCreateProfileActivity.this, UserDashboardActivity.class);
                                                        startActivity(intent);
                                                        Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        dialog.hide();
                                                    }
                                                }
                                            });

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });
        }else{
            Toast.makeText(getApplicationContext(), "please Select the Image", Toast.LENGTH_SHORT).show();
        }

    }
    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Exit();
        }
    };
    private void Exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" Gym Managment App");
        builder.setMessage("Are you sure you want to exit the Gym Managment App?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
    protected void onStart() {
        super.onStart();
        ProgressDialog progressDialog=new ProgressDialog(UserCreateProfileActivity.this);
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String currentUsers=user.getUid().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference profilesRef = database.getReference("GymUser").child("Details");
        profilesRef.child(currentUsers).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Profile exists, navigate to another activity
                    startActivity(new Intent(UserCreateProfileActivity.this, UserDashboardActivity.class));
                } else {
                    // Profile does not exist
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // An error occurred while querying the database
            }
        });

    }

}
