package com.example.powerstrentgh.User.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.ModelCLass.AddUserDetailToRealtime;
import com.example.powerstrentgh.ModelCLass.BookingTrainnerDetailModelClass;
import com.example.powerstrentgh.ModelCLass.BookingUserDetail;
import com.example.powerstrentgh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentViewTrainnerProfile extends Fragment {

    String userUid,currentimageurl,Trainnername;
    ImageView img;
    TextView tvview;
    Button btn;
    ProgressDialog dialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    String currentuserID,currentname,currentimage;
    public FragmentViewTrainnerProfile() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_trainner_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        currentuserID = user.getUid();
        setData(view);
        BookTrainner(view);
        GetCurrentuserDetails();
        return  view;
    }
    private void setData(View view){
        userUid = getArguments().getString("uid");
        currentimageurl = getArguments().getString("imageurl");
        Trainnername = getArguments().getString("Trainnnername");
        img=view.findViewById(R.id.imageView5);
        tvview=view.findViewById(R.id.textView16);
        Glide.with(getContext()).load(currentimageurl).into(img);
        tvview.setText(Trainnername);
    }
    private void BookTrainner(View view){
       btn=view.findViewById(R.id.boooktrainnerID);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
           BookingDetailtoRealtime();
           }
       });
    }
    private void BookingDetailtoRealtime(){
        dialog=new ProgressDialog(getContext());
        dialog.setTitle("Power Strength");
        dialog.setMessage("Please wait");
        BookingTrainnerDetailModelClass obj =new  BookingTrainnerDetailModelClass(currentuserID,currentname,currentimage,userUid);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("TrainnerBookingDetail").child("Trainner");
        databaseReference.child(userUid).push().setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
                    ShowBookTRainner();
                    Toast.makeText(getContext(), "Sucessfully book", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ShowBookTRainner(){
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("UserBookingDetail").child("User");
        BookingUserDetail obj=new BookingUserDetail(Trainnername,currentimageurl,userUid,currentuserID);
        databaseReference.child(currentuserID).push().setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Sucessfully book", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetCurrentuserDetails(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference("VisitProfile").child(
                "User").child(currentuserID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    AddUserDetailToRealtime obj=snapshot.getValue(AddUserDetailToRealtime.class);
                    currentimage=obj.getImageul();
                    currentname=obj.getCurentusername();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}