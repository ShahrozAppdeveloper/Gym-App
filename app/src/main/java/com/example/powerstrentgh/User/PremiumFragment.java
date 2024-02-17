package com.example.powerstrentgh.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.powerstrentgh.ModelCLass.BuyPremiumModelCass;
import com.example.powerstrentgh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PremiumFragment extends Fragment {


    FirebaseAuth mAuth;
    FirebaseUser user;
    String uid;
    Button btn;
    FirebaseDatabase database;
    DatabaseReference ref;
    String name;
    String email;
    public PremiumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_premium, container, false);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();
        BuyPremium(view);
        Bundle args = getArguments();
        if (args != null) {
             name = args.getString("name");
             email = args.getString("email");

        }

        return view;
    }
    private void BuyPremium(View view){
        btn=view.findViewById(R.id.buypremium);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Upload();
            }
        });
    }
    private void Upload(){
        BuyPremiumModelCass obj =new BuyPremiumModelCass(uid,name,"Premium",email);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Buy Premuim");
        ref.child("Premuim").child(uid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Your Plan is Premium Now", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}