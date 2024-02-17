package com.example.powerstrentgh.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.powerstrentgh.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserDashBoard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        bottomNavigationView=findViewById(R.id.bottomnavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);
        replacefragment(new Home());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment=new Home();
                        break;
                    case R.id.coach:
                        fragment=new UsercoachFragment();
                        break;
                    case R.id.schedule:
                        fragment=new Mangeschedule();
                        break;
                    case R.id.profile:
                        fragment=new Profileuser();
                        break;
                    case R.id.addchallenge:
                        fragment=new UserChallengeFragment();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                return true;
            }
        });
    }
    public void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
