package com.example.powerstrentgh.Developer.UserPanel.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.powerstrentgh.Admin.AdminAdapter.AdminTrainnerAdapter;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.Adapter.TrainnerToUserAdapter;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentUnBookUserTrainnerBinding;
import com.example.powerstrentgh.databinding.FragmentUnProvedTrainnerToAdminBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UnBookUserTrainnerFragment extends Fragment {


    private FragmentUnBookUserTrainnerBinding binding;
    private TrainnerToUserAdapter adapter;
    private ArrayList<AddTrainnerDetailToDatabase> list;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    public UnBookUserTrainnerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUnBookUserTrainnerBinding.inflate(getLayoutInflater(),container,false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if (user != null) {
            String currentUserId = user.getUid();
            list = new ArrayList<>();
            adapter = new TrainnerToUserAdapter(list, requireActivity(), currentUserId);
            binding.progressB.setVisibility(View.VISIBLE);

            reference = FirebaseDatabase.getInstance().getReference().child("GymTrainner").child("Details");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        AddTrainnerDetailToDatabase model = snapshot1.getValue(AddTrainnerDetailToDatabase.class);
                        if (model != null && "notbook".equals(model.getBookingstatus()) && "proved".equals(model.getTrainnerstatus())) {
                            list.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    binding.progressB.setVisibility(View.GONE);

                    if (list.isEmpty()) {
                        Toast.makeText(requireActivity(), "No Trainer found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    binding.progressB.setVisibility(View.GONE);
                    Toast.makeText(requireActivity(), "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            binding.trainneruserRecyclerViewID.setAdapter(adapter);
            binding.trainneruserRecyclerViewID.setLayoutManager(new LinearLayoutManager(requireActivity()));
        } else {
            // Handle the case where the user is null
        }

        return binding.getRoot();
    }
}