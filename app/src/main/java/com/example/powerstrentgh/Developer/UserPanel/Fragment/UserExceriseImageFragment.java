package com.example.powerstrentgh.Developer.UserPanel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powerstrentgh.Developer.UserPanel.Adapter.ImageToUserAdapter;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.ExceriseImageModelClass;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentUnBookUserTrainnerBinding;
import com.example.powerstrentgh.databinding.FragmentUserExceriseImageBinding;

import java.util.ArrayList;

public class UserExceriseImageFragment extends Fragment {
    private FragmentUserExceriseImageBinding binding;
    private ImageToUserAdapter adapter;
    ArrayList<ExceriseImageModelClass> datalist;
    public UserExceriseImageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserExceriseImageBinding.inflate(getLayoutInflater(),container,false);


        datalist = new ArrayList<>();

        return binding.getRoot();
    }
}