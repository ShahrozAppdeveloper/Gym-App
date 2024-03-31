package com.example.powerstrentgh.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.powerstrentgh.Admin.Fragment.AdminHomeFragment;
import com.example.powerstrentgh.Admin.Fragment.ListOfTrainnerToAdminFragment;
import com.example.powerstrentgh.Admin.Fragment.ListofTUserToAdminFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.ListofTrainnerTouserFragment;
import com.example.powerstrentgh.Developer.UserPanel.Fragment.UserHomeFragment;
import com.example.powerstrentgh.Developer.UserPanel.UserDashboardActivity;
import com.example.powerstrentgh.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminDashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.black));

        bottomNavigationView = findViewById(R.id.adminbottomNavigationView);
        ChangeFragment(new AdminHomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuId = item.getItemId();
                if (menuId == R.id.home) {
                    ChangeFragment(new AdminHomeFragment());
                } else if (menuId == R.id.coach) {
                    ChangeFragment(new ListOfTrainnerToAdminFragment());
                } else if (menuId == R.id.userlist) {
                    ChangeFragment(new ListofTUserToAdminFragment());
                } else if (menuId == R.id.profiletrainer) {
//                    ChangeFragment(new StudentProfileFragment());
                }   else {


                }
                return true;
            }
        });
    }
    public void ChangeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrames,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboard.this);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finishAffinity();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}