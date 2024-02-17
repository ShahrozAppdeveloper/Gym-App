package com.example.powerstrentgh.Trainner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.powerstrentgh.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Trainerdashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainerdashboard);
        bottomNavigationView=findViewById(R.id.bottomnavigationtrainer);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);
        replacefragment(new TrainerScheduleFragment());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.hometrainer:
                        fragment=new TrainerScheduleFragment();
                        break;
                    case R.id.schedulemanage:
                        fragment=new TrainerCreateWorkout();
                        break;
                    case R.id.bookedusersrv:
                        fragment=new TrainerUsers();
                        break;
                    case R.id.progress:
                        fragment=new ProgressFragment();
                        break;
                    case R.id.profiletrainer:
                        fragment=new TrainerProfile();
                        break;
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
