package com.example.powerstrentgh.Trainner;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.powerstrentgh.ModelCLass.AddtrainnerDetailTorealtime;
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

import java.io.IOException;
import java.util.UUID;

public class TrainnerProfileActivity extends AppCompatActivity {
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    ImageView imagepick;
    String currentUser;
    ProgressDialog dialog;
    EditText edname,edcnicnumber;
    FirebaseStorage storage;
    Button btncreateprofile;
    private FirebaseAuth mAuth;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String username,cnicnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainner_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.firstcolor));
        }
        Ids();
        Clicklistener();
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }
    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Exit();
        }
    };
    private void Exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Power Strength");
        builder.setMessage("Are you sure you want to exit the Power Strength?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
    private void Ids(){
        imagepick=findViewById(R.id.imageView2);
        edname=findViewById(R.id.textnameid);
        btncreateprofile=findViewById(R.id.btncreateid);
    }
    private void Clicklistener(){
        imagepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImage();
            }
        });
        btncreateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=edname.getText().toString().trim();

                if(username.isEmpty()
                ){
                    Toast.makeText(getApplicationContext(), "Please Fill Details", Toast.LENGTH_SHORT).show();
                }else{
                    UploadImageNdDetails();
                    dialog = new ProgressDialog(TrainnerProfileActivity.this);
                    dialog.setMessage("please wait...");
                    dialog.show();

                }
            }
        });
    }
    private void PickImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,
                resultCode,
                data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                        filePath);
                imagepick.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void UploadImageNdDetails()
    {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("VisitProfile").child("Trainner");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        currentUser = user.getUid();

        if (filePath != null) {
            StorageReference ref = storageReference.child("images/"
                    + UUID.randomUUID().toString());
            ref.putFile(filePath)
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
                                            AddtrainnerDetailTorealtime obj=new AddtrainnerDetailTorealtime(currentUser,username,imageurl);
                                            databaseReference.child(currentUser).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        dialog.dismiss();
                                                        Intent intent=new Intent(TrainnerProfileActivity.this,TrainerCertificateActivity.class);
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

    @Override
    protected void onStart() {
        super.onStart();
        ProgressDialog progressDialog=new ProgressDialog(TrainnerProfileActivity.this);
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String currentUsers=user.getUid().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference profilesRef = database.getReference("VisitProfile").child("Trainner");
        profilesRef.child(currentUsers).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Profile exists, navigate to another activity
                    startActivity(new Intent(TrainnerProfileActivity.this, Trainerdashboard.class));
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