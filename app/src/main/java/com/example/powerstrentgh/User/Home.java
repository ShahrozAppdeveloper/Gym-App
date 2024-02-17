package com.example.powerstrentgh.User;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.powerstrentgh.AdapterClass;
import com.example.powerstrentgh.ClassAdapter.TrackProgressUserAdapter;
import com.example.powerstrentgh.CoachPopularAdapter;
import com.example.powerstrentgh.ModelCLass.BookingUserDetail;
import com.example.powerstrentgh.ModelCLass.TrackProgressModelClass;
import com.example.powerstrentgh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Home extends Fragment {

RecyclerView recyclerView,recyclerView1,recyclerView2;
AdapterClass adapterClass;
TrackProgressUserAdapter adapterclasses;
    ImageSlider imageSlider;
    ArrayList<TrackProgressModelClass> arrayList;
    ProgressDialog dialog;
    ArrayList<BookingUserDetail> datalist;
   CoachPopularAdapter adapters;
    FirebaseAuth mAuth;
    FirebaseUser user;
    public Home() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        imageSlider=view.findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slider1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider6, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        GetListOfTrainerWorkout(view);
        GetlistofTrainner(view);
        return view;
    }

    private void GetListOfTrainerWorkout(View view) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserId = currentUser.getUid();
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.mainrv);
        arrayList = new ArrayList<>();
        adapterclasses = new TrackProgressUserAdapter(getContext(), arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapterclasses);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("Track Progress user")
                .child("Progress")
                .child(currentUserId);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                TrackProgressModelClass obj = dataSnapshot.getValue(TrackProgressModelClass.class);
                arrayList.add(obj);
                adapterclasses.notifyDataSetChanged();
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
    }




    private void GetlistofTrainner(View view) {

        String uid = user.getUid();
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.coaches);

        datalist = new ArrayList<>();
        adapters = new CoachPopularAdapter(getContext(),datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapters);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference(
                "UserBookingDetail").child(
                "User");

        ref.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        BookingUserDetail obj = dataSnapshot.getValue(BookingUserDetail.class);
                        datalist.add(obj);
                    }

                    adapters.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }



}