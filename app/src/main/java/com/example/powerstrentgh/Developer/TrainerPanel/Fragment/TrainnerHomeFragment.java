package com.example.powerstrentgh.Developer.TrainerPanel.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.powerstrentgh.R;
import com.example.powerstrentgh.SharedPrefPkg.PrefManager;
import com.example.powerstrentgh.User.Signup;
import com.example.powerstrentgh.databinding.FragmentTrainerHomeBinding;
import com.google.firebase.auth.FirebaseAuth;


public class TrainnerHomeFragment extends Fragment {
    private FragmentTrainerHomeBinding binding;


    public TrainnerHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTrainerHomeBinding.inflate(getLayoutInflater(),container,false);
        Logout();
        return binding.getRoot();
    }
    private void Logout(){
        binding.createtrainerprofilebuttonid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                ProgressDialog progressDialog=new ProgressDialog(requireContext());
                progressDialog.setMessage("Signout");
                progressDialog.show();
                PrefManager prefManager=new PrefManager(requireContext());
                prefManager.setCurrentstatus("");
                progressDialog.dismiss();
                Toast.makeText(requireContext(), "Signout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(requireContext(), Signup.class));
            }
        });
    }
}