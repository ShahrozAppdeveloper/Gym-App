package com.example.powerstrentgh.Admin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powerstrentgh.Admin.AdminViewPagerAdapter.TrainnerToAdminViewPagerAdapter;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.AdapterViewPager.TrainnerTouserViewPagerAdapter;
import com.example.powerstrentgh.ModelCLass.AddtrainnerDetailTorealtime;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentListOfTrainnerToAdminBinding;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class ListOfTrainnerToAdminFragment extends Fragment {

   private FragmentListOfTrainnerToAdminBinding binding;
    private TrainnerToAdminViewPagerAdapter adapter;

    public ListOfTrainnerToAdminFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListOfTrainnerToAdminBinding.inflate(getLayoutInflater(),container,false);
        adapter = new TrainnerToAdminViewPagerAdapter(
                getChildFragmentManager());
        binding. viewPager.setAdapter(adapter);

        // It is used to join TabLayout with ViewPager.
        binding. tabLayout.setupWithViewPager( binding. viewPager);
        return  binding.getRoot();
    }

}