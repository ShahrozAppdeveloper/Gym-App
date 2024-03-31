package com.example.powerstrentgh.Admin.AdminViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.powerstrentgh.Admin.AdminViewPagerFragment.ProvedTrainnerToAdminfragment;
import com.example.powerstrentgh.Admin.AdminViewPagerFragment.UnProvedTrainnerToAdminFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.BookUserTrainnerFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.UnBookUserTrainnerFragment;


public class TrainnerToAdminViewPagerAdapter
 extends FragmentPagerAdapter {

    public TrainnerToAdminViewPagerAdapter(
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
            fragment = new UnProvedTrainnerToAdminFragment();
        else if (position == 1) 
            fragment = new ProvedTrainnerToAdminfragment();


  
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
            title = "not Approved";
        else if (position == 1) 
            title = "Approved";
        return title; 
    } 
} 