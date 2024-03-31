package com.example.powerstrentgh.Admin.AdminViewPagerFragment;

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
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentProvedTrainnerToAdminfragmentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProvedTrainnerToAdminfragment extends Fragment {
   private FragmentProvedTrainnerToAdminfragmentBinding binding;
    private AdminTrainnerAdapter adapter;
    ArrayList<AddTrainnerDetailToDatabase> list;
    DatabaseReference reference;

    public ProvedTrainnerToAdminfragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProvedTrainnerToAdminfragmentBinding.inflate(getLayoutInflater(),container,false);
        list = new ArrayList<>();

        adapter = new AdminTrainnerAdapter(list, requireActivity());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("GymTrainner").child("Details");

        reference
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        if (snapshot.exists()){
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                AddTrainnerDetailToDatabase model = snapshot1.getValue(AddTrainnerDetailToDatabase.class);
                                String status = model.getTrainnerstatus();
                                if (status.equals("proved")) {
                                    list.add(model);
                                }
                            }
                            binding.progressB.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }else{
                            binding.progressB.setVisibility(View.GONE);
                            Toast.makeText(requireActivity(), "no Trainner found", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle onCancelled
                    }
                });

        binding.trainnerAdminRecyclerViewID.setAdapter(adapter);
        binding.trainnerAdminRecyclerViewID.setLayoutManager(new LinearLayoutManager(requireActivity()));
        return  binding.getRoot();
    }

}