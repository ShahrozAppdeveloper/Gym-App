package com.example.powerstrentgh.Developer.TrainerPanel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powerstrentgh.R;


public class CreateWorkoutForMemberFragment extends Fragment {


    public CreateWorkoutForMemberFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_workout_for_member, container, false);
    }
}