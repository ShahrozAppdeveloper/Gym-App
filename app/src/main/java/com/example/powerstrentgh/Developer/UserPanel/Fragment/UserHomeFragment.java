package com.example.powerstrentgh.Developer.UserPanel.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powerstrentgh.Developer.UserPanel.Adapter.SliderAdapter;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.SliderData;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.SharedPrefPkg.PrefManager;
import com.example.powerstrentgh.User.Signup;
import com.example.powerstrentgh.databinding.FragmentTrainerHomeBinding;
import com.example.powerstrentgh.databinding.FragmentUserHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class UserHomeFragment extends Fragment {

   private FragmentUserHomeBinding binding;

    public UserHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserHomeBinding.inflate(getLayoutInflater(),container,false);
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        sliderDataArrayList.add(new SliderData(R.drawable.header_viewer));
        sliderDataArrayList.add(new SliderData(R.drawable.header));
        sliderDataArrayList.add(new SliderData(R.drawable.header_viewer));
        sliderDataArrayList.add(new SliderData(R.drawable.header));

        SliderAdapter adapter = new SliderAdapter(requireActivity(), sliderDataArrayList);
        binding.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        binding.slider.setSliderAdapter(adapter);
        binding.slider.setScrollTimeInSec(3);
        binding.slider.setAutoCycle(true);
        binding.slider.startAutoCycle();
        String fitnessParagraph = "A fitness app is a powerful tool that seamlessly integrates into our daily lives, serving as a virtual coach and companion on our journey to better health. Whether you're a seasoned athlete or just starting out, these apps offer a plethora of features tailored to individual goals and preferences. From personalized workout routines and nutrition plans to real-time tracking of progress and achievements, they empower users to take control of their fitness journey anytime, anywhere. With the convenience of accessing workouts at home or on the go, coupled with interactive challenges and community support, fitness apps revolutionize the way we approach health and wellness, making it more accessible, engaging, and enjoyable for everyone.";

        binding.fitnessParagraphTextView.setText(fitnessParagraph);
        return  binding.getRoot();
    }
    private void Logout(View view){

    }

}