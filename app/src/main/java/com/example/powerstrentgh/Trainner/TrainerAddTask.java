package com.example.powerstrentgh.Trainner;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.powerstrentgh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;


public class TrainerAddTask extends Fragment {

ProgressDialog progressDialog;
EditText title,type,day;
Button addtask;
ImageView imageView;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri imageUri ;

String clientcurrentuserID,currentname,currentimage;
    public final int GALLERY_REQ_CODE=1000;

    public TrainerAddTask() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trainer_add_task, container, false);
        addtask=view.findViewById(R.id.upload);
        setData(view);
        imageView=view.findViewById(R.id.uploadimg);
        title=view.findViewById(R.id.addtasktitle);
        type=view.findViewById(R.id.addtasktype);
        day=view.findViewById(R.id.addtaskday);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
imgget();

            }
        });
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();

            }
        });
        setData(view);
        return view;

    }
    private String titles,types,days;
    public void validatedata(){
      titles=title.getText().toString().trim();
     types=type.getText().toString().trim();
     days=day.getText().toString().trim();
    if(titles.isEmpty()){
        Toast.makeText(getContext(), "Title is Empty", Toast.LENGTH_SHORT).show();
    }else if(types.isEmpty()){
        Toast.makeText(getContext(), "Please Enter Meal Or Wokout", Toast.LENGTH_SHORT).show();
    }else if(days.isEmpty()){
        Toast.makeText(getContext(), "ENTER Days ", Toast.LENGTH_SHORT).show();
    } else if (imageUri==null) {
        Toast.makeText(getContext(), "Pick Img....", Toast.LENGTH_SHORT).show();
    } else{
uploadimage();
    }

}
    private void setData(View view) {
        clientcurrentuserID = getArguments().getString("uid");
        currentimage = getArguments().getString("imageurl");
        currentname = getArguments().getString("username");

    }
    private void senddatatodb(String imageUrl,long timestamp) {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading Task For User");
        progressDialog.show();
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        String CurrentUserId=user.getUid();
        HashMap <String,Object> hashMap=new HashMap<>();
    hashMap.put("CurrentUserId",""+CurrentUserId);
    hashMap.put("TaskTitle",""+titles);
    hashMap.put("TaskType",""+types);
    hashMap.put("TaskDay",""+days);
    hashMap.put("Imageurl",""+imageUrl);
    hashMap.put("timestamp",timestamp);
        hashMap.put("CilentuserID",""+clientcurrentuserID);
    hashMap.put("cilentusername",""+currentname);
    hashMap.put("cilentuserimage",""+currentimage);
        DatabaseReference  reference= FirebaseDatabase.getInstance().getReference("UserTaskDetails").child("Task");
        reference.child(""+clientcurrentuserID).push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "Data Uploded", Toast.LENGTH_SHORT).show();
                TrainnerTaskDetial(hashMap,CurrentUserId,imageUrl);
                progressDialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed To Upload", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void TrainnerTaskDetial(HashMap hashMap,String Id,String imageurl){
        DatabaseReference  reference= FirebaseDatabase.getInstance().getReference("TrainnerTaskDetails").child("Task");
        reference.child(""+Id).push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed To Upload", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Bundle bundle=getArguments();
            Fragment fragment=new TrainerCreateWorkout();
            fragment.setArguments(bundle);
            ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    };

    // Register the callback in the onCreate() method
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    // Unregister the callback in the onDestroy() method
    @Override
    public void onDestroy() {
        super.onDestroy();
        onBackPressedCallback.remove();
    }
    private void imgget() {
        Intent iGallrey= new Intent(Intent.ACTION_PICK);
        iGallrey.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallrey,GALLERY_REQ_CODE);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if (requestCode==GALLERY_REQ_CODE){
                imageUri= data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }
    private void uploadimage() {
//        progressDialog.setTitle("Uploading....");
//        progressDialog.setMessage("Please Wait");
//        progressDialog.show();
        storage = FirebaseStorage.getInstance();
        HashMap hashMap = null;
        String Id = null;
        storageReference = storage.getReference("Task");
        String filepathname = "Tasks";
        long timestamp=System.currentTimeMillis();
        StorageReference ref = storageReference.child(filepathname);
        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Image uploaded successfully, get the download URL
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                TrainnerTaskDetial(hashMap,Id,imageUrl);
                                // Now, you can use 'imageUrl' as needed
                                // For example, you can save it to the database or display it in an ImageView
                                senddatatodb(imageUrl,timestamp); // Pass the URL to your senddatatodb method
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Failed to get download URL", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed to upload image", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }



}