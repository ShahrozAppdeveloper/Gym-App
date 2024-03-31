package com.example.powerstrentgh.Developer.TrainerPanel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powerstrentgh.Developer.TrainerPanel.AdapterClass.UserToTrainnerViewPagerAdapter;
import com.example.powerstrentgh.Developer.UserPanel.AdapterViewPager.TrainnerTouserViewPagerAdapter;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentAllRequestUserToTrainnerBinding;
import com.example.powerstrentgh.databinding.FragmentConfrrimedBookingUserTOTrainnerBinding;
import com.example.powerstrentgh.databinding.FragmentListOFUserToTrainnerBinding;


public class ListOFUserToTrainnerFragment extends Fragment {

     private FragmentListOFUserToTrainnerBinding binding;
     private UserToTrainnerViewPagerAdapter adapter;
    public ListOFUserToTrainnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListOFUserToTrainnerBinding.inflate(getLayoutInflater(),container,false);
        adapter = new UserToTrainnerViewPagerAdapter(
                getChildFragmentManager());
        binding. viewPager.setAdapter(adapter);

        // It is used to join TabLayout with ViewPager.
        binding. tabLayout.setupWithViewPager( binding. viewPager);
        return binding.getRoot();
    }
}