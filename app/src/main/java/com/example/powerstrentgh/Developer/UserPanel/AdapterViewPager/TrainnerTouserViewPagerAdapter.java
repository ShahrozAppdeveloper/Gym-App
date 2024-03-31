package com.example.powerstrentgh.Developer.UserPanel.AdapterViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.powerstrentgh.Developer.UserPanel.Fragment.BookUserTrainnerFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.PendingBookingDetailsUserFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.UnBookUserTrainnerFragment;


public class TrainnerTouserViewPagerAdapter
 extends FragmentPagerAdapter {

    public TrainnerTouserViewPagerAdapter(
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
            fragment = new UnBookUserTrainnerFragment();
        else if (position == 1) 
            fragment = new PendingBookingDetailsUserFragment();
        else if (position == 2)
            fragment = new BookUserTrainnerFragment();


  
        return fragment; 
    } 
  
    @Override
    public int getCount() 
    { 
        return 3;
    } 
  
    @Override
    public CharSequence getPageTitle(int position) 
    { 
        String title = null; 
        if (position == 0) 
            title = "UnBook";
        else if (position == 1) 
            title = "Pending";
        else if (position == 2)
            title = "Book";
        return title; 
    } 
} 