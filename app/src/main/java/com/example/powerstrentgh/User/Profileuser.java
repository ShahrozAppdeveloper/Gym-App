package com.example.powerstrentgh.User;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.powerstrentgh.ModelCLass.CurrentStatusDetails;
import com.example.powerstrentgh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profileuser extends Fragment {

private FirebaseUser user;
private DatabaseReference reference;
 String uid;
TextView uname,emails,premium;
Button logout;

    public Profileuser() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profileuser, container, false);
       getuserdetails(view);
       logout=view.findViewById(R.id.logout);
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FirebaseAuth.getInstance().signOut(); //signout firebase
               Intent setupIntent = new Intent(getContext(),Front.class);
               Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_LONG).show(); //if u want to show some text
               setupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(setupIntent);

           }
       });
       premium=view.findViewById(R.id.premium);
       premium.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Profileuser firstFragment = new Profileuser();
               PremiumFragment secondFragment = new PremiumFragment();
               Bundle args = new Bundle();
               args.putString("name", uname.getText().toString());
               args.putString("email", emails.getText().toString());
               secondFragment.setArguments(args);


// Get the FragmentManager
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

// Start a fragment transaction
               FragmentTransaction transaction = fragmentManager.beginTransaction();

// Replace the current fragment with the new one
               transaction.replace(R.id.container, secondFragment); // R.id.fragment_container is the ID of the container in your layout

// Optionally, add the transaction to the back stack
               transaction.addToBackStack(null);

// Commit the transaction
               transaction.commit();
           }
       });
        return view;
    }


    public void getuserdetails(View view){
        uname=view.findViewById(R.id.username);
        emails=view.findViewById(R.id.emails);

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
    }
    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Bundle bundle=getArguments();
            Fragment fragment=new Home();
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