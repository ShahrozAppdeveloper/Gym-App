package com.example.powerstrentgh.Developer.TrainerPanel.AdapterClass;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.powerstrentgh.Developer.TrainerPanel.Fragment.AllRequestUserToTrainnerFragment;
import com.example.powerstrentgh.Developer.TrainerPanel.Fragment.ConfrrimedBookingUserTOTrainnerFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.BookUserTrainnerFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.PendingBookingDetailsUserFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.UnBookUserTrainnerFragment;


public class UserToTrainnerViewPagerAdapter
 extends FragmentPagerAdapter {

    public UserToTrainnerViewPagerAdapter(
@NonNull FragmentManager fm)
    { 
        super(fm); 
    } 
  
    @NonNull
    @Override
    public Fragment getItem(int position)
    { 
        Fragment fragment = null; 
        if (position == 0)
            fragment = new AllRequestUserToTrainnerFragment();
        else if (position == 1) 
            fragment = new ConfrrimedBookingUserTOTrainnerFragment();



  
        return fragment; 
    } 
  
    @Override
    public int getCount() 
    { 
        return 2;
    } 
  
    @Override
    public CharSequence getPageTitle(int position) 
    { 
        String title = null; 
        if (position == 0) 
            title = "All Request";
        else if (position == 1) 
            title = "Booked";

        return title; 
    } 
} 