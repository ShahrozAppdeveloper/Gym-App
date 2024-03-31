package com.example.powerstrentgh.Developer.UserPanel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.powerstrentgh.Developer.UserPanel.AdapterViewPager.TrainnerTouserViewPagerAdapter;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentListofTrainnerTouserBinding;


public class ListofTrainnerTouserFragment extends Fragment {
    private FragmentListofTrainnerTouserBinding binding;
    private TrainnerTouserViewPagerAdapter adapter;

    public ListofTrainnerTouserFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListofTrainnerTouserBinding.inflate(inflater,container,false);

        adapter = new TrainnerTouserViewPagerAdapter(
                getChildFragmentManager());
       binding. viewPager.setAdapter(adapter);

        // It is used to join TabLayout with ViewPager.
       binding. tabLayout.setupWithViewPager( binding. viewPager);







        return  binding.getRoot();

    }
}