package com.example.powerstrentgh.Admin.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.powerstrentgh.Admin.AdminAdapter.AdminTrainnerAdapter;
import com.example.powerstrentgh.Admin.AdminAdapter.UserToAdminAdapter;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.AdduserDetailsToDatabase;
import com.example.powerstrentgh.ModelCLass.AddUserDetailToRealtime;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentListOfTrainnerToAdminBinding;
import com.example.powerstrentgh.databinding.FragmentListofTUserToAdminBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListofTUserToAdminFragment extends Fragment {

    private FragmentListofTUserToAdminBinding binding;
    private UserToAdminAdapter adapter;
    ArrayList<AdduserDetailsToDatabase> list;
    DatabaseReference reference;
    public ListofTUserToAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListofTUserToAdminBinding.inflate(getLayoutInflater(),container,false);
        list = new ArrayList<>();

        adapter = new UserToAdminAdapter(list, requireActivity());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("GymUser").child(
                "Details");

        reference
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        if (snapshot.exists()){
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                AdduserDetailsToDatabase model = snapshot1.getValue(AdduserDetailsToDatabase.class);
                                    list.add(model);

                            }
                            binding.progressB.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }else{
                            binding.progressB.setVisibility(View.GONE);
                            Toast.makeText(requireActivity(), "no user found", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle onCancelled
                    }
                });

        binding.userAdminRecyclerViewID.setAdapter(adapter);
        binding.userAdminRecyclerViewID.setLayoutManager(new GridLayoutManager(requireActivity(),2));
        return  binding.getRoot();
    }
}