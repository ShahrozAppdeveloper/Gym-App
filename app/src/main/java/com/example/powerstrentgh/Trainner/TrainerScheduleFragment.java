package com.example.powerstrentgh.Trainner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.powerstrentgh.AdapterAllUser;
import com.example.powerstrentgh.ModelCLass.TaskManagerModelClass;
import com.example.powerstrentgh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TrainerScheduleFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<TaskManagerModelClass> list;
    AdapterAllUser adapterAllUser;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String uid;
    ImageSlider imageSlider;
ImageView imageView;
    public TrainerScheduleFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trainer_home, container, false);
        imageSlider=view.findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slider1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider6, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
imageView=view.findViewById(R.id.chatuser);
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        TrainerScheduleFragment firstFragment = new TrainerScheduleFragment();
        TrainerBookFragment secondFragment = new TrainerBookFragment();

// Get the FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

// Start a fragment transaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();

// Replace the current fragment with the new one
        transaction.replace(R.id.container, secondFragment); // R.id.fragment_container is the ID of the container in your layout

// Optionally, add the transaction to the back stack
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();

    }
});
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();
        trainerscedules(view,uid);
        return view;
    }




    private void trainerscedules(View view, String userid) {
        recyclerView = view.findViewById(R.id.allusersrv);
        if (userid != null) {
            list = new ArrayList<>();
            adapterAllUser = new AdapterAllUser(list, getContext());
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("TrainnerTaskDetails").child("Task").child(userid);

            ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                    TaskManagerModelClass obj = dataSnapshot.getValue(TaskManagerModelClass.class);
                    list.add(obj);
                    adapterAllUser.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                    // Handle child data changes if needed
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    // Handle child data removal if needed
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                    // Handle child data movement if needed
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapterAllUser);
        } else {
            Toast.makeText(getContext(), "not user yet", Toast.LENGTH_SHORT).show();
        }
    }




}