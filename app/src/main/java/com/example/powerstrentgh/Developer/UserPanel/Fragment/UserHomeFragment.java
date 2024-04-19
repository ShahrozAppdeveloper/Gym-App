package com.example.powerstrentgh.Developer.UserPanel.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        Logout();
        return  binding.getRoot();
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