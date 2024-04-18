package com.example.powerstrentgh.Developer.UserPanel.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.Adapter.BookedUserToTrainnerAdapter;
import com.example.powerstrentgh.Developer.UserPanel.Adapter.BookingProcessTrainnerToUserAdapter;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.MemberBookingDetails;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentAllRequestUserToTrainnerBinding;
import com.example.powerstrentgh.databinding.FragmentBookUserTrainnerBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BookUserTrainnerFragment extends Fragment {


   private FragmentBookUserTrainnerBinding binding;
   private BookedUserToTrainnerAdapter adapter;
    ArrayList<AddTrainnerDetailToDatabase> list;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    public BookUserTrainnerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookUserTrainnerBinding.inflate(getLayoutInflater(), container, false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if (user != null) {
            String currentUserId = user.getUid();
            list = new ArrayList<>();
            adapter = new BookedUserToTrainnerAdapter(list, requireActivity());
            binding.progressB.setVisibility(View.VISIBLE);

            reference = FirebaseDatabase.getInstance().getReference("MemberBooking").child("Info");

            reference.child(currentUserId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MemberBookingDetails model = dataSnapshot.getValue(MemberBookingDetails.class);
                            if (model != null && "Accept".equals(model.getReqstatus())) {
                                String trainerId = model.getTrainnerID();
                                DatabaseReference trainerRef = FirebaseDatabase.getInstance().getReference("GymTrainner").child("Details").child(trainerId);
                                trainerRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            AddTrainnerDetailToDatabase trainerDetail = snapshot.getValue(AddTrainnerDetailToDatabase.class);
                                            if (trainerDetail != null) {
                                                list.add(trainerDetail);
                                            }
                                        }
                                        adapter.notifyDataSetChanged();
                                        binding.progressB.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        binding.progressB.setVisibility(View.GONE);
                                        Toast.makeText(requireActivity(), "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    } else {
                        binding.progressB.setVisibility(View.GONE);
                        Toast.makeText(requireActivity(), "No  Booking found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    binding.progressB.setVisibility(View.GONE);
                    Toast.makeText(requireActivity(), "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            binding.bookuserRecyclerViewID.setAdapter(adapter);
            binding.bookuserRecyclerViewID.setLayoutManager(new LinearLayoutManager(requireActivity()));
        } else {
            // Handle the case where the user is null
        }

        return  binding.getRoot();
    }
}