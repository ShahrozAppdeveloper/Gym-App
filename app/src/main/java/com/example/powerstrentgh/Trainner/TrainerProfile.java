package com.example.powerstrentgh.Trainner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.powerstrentgh.ModelCLass.CurrentStatusDetails;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.User.Front;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TrainerProfile extends Fragment {
    private FirebaseUser user;
    private DatabaseReference reference;
    String uid;

    TextView uname,emails;
Button logout;
    public TrainerProfile() {
        // Required empty public constructor
    }




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trainer_profile, container, false);
        uname=view.findViewById(R.id.trname);
        emails=view.findViewById(R.id.tremail);
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut(); //signout firebase
                Intent setupIntent = new Intent(getContext(), Front.class);
                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_LONG).show(); //if u want to show some text
                setupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(setupIntent);

            }
        });
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Gym App").child("Account Details");
        uid= user.getUid();
        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CurrentStatusDetails currentStatusDetails=snapshot.getValue(CurrentStatusDetails.class);
                if(currentStatusDetails!=null){
                    String Fullname=currentStatusDetails.getFullname();
                    String Email=currentStatusDetails.getEmail();

                    uname.setText(Fullname);
                    emails.setText(Email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Bundle bundle=getArguments();
            Fragment fragment=new TrainerScheduleFragment();
            fragment.setArguments(bundle);
            ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    };

    // Register the callback in the onCreate() method
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    // Unregister the callback in the onDestroy() method
    @Override
    public void onDestroy() {
        super.onDestroy();
        onBackPressedCallback.remove();
    }

}
