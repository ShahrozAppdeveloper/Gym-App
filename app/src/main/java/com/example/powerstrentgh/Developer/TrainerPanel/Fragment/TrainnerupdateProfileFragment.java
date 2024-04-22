package com.example.powerstrentgh.Developer.TrainerPanel.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.AdduserDetailsToDatabase;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.SharedPrefPkg.PrefManager;
import com.example.powerstrentgh.User.MainActivity;
import com.example.powerstrentgh.databinding.FragmentMemberUpdateProfileBinding;
import com.example.powerstrentgh.databinding.FragmentTrainnerupdateProfileBinding;
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

import java.util.HashMap;
import java.util.Map;


public class TrainnerupdateProfileFragment extends Fragment {


    private FragmentTrainnerupdateProfileBinding binding;
    ProgressDialog dialog;
    private FirebaseAuth mAuth;
    String userUid;
    public TrainnerupdateProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTrainnerupdateProfileBinding.inflate(getLayoutInflater(), container, false);
        SetDetails();
        binding.updateTeacherProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateprofileID();
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("please wait...");
                dialog.show();
            }
        });
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                ProgressDialog progressDialog=new ProgressDialog(requireActivity());
                progressDialog.setMessage("Signout");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PrefManager prefManager=new PrefManager(requireActivity());
                        prefManager.setCurrentstatus("");
                        prefManager.setUserID("");
                        progressDialog.dismiss();
                        Toast.makeText(requireActivity(), "Signout", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(requireActivity(), MainActivity.class));
                    }
                },3000);
            }
        });
        return  binding.getRoot();
    }
    private void SetDetails() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userUid = user.getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference(
                "GymTrainner").child("Details").child(userUid.toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    AddTrainnerDetailToDatabase obj = snapshot.getValue(AddTrainnerDetailToDatabase.class);
                    Glide.with(getContext()).load(obj.getTrainnerimageurl()).into(binding.circleImageView);
                    binding.edfirstnameid.setText(obj.getTrainnername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateprofileID() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("GymTrainner").child("Details").child(userUid);
        Map<String, Object> updates = new HashMap<>();
        updates.put("trainnername", binding.edfirstnameid.getText().toString().trim());
        usersRef.updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }
}