package com.example.powerstrentgh.Developer.TrainerPanel.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.powerstrentgh.Developer.TrainerPanel.AdapterClass.ReqUserToTrainnerAdapter;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.Adapter.BookingProcessTrainnerToUserAdapter;
import com.example.powerstrentgh.Developer.UserPanel.AdduserDetailsToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.AddRequserDetailsToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.MemberBookingDetails;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.databinding.FragmentAllRequestUserToTrainnerBinding;
import com.example.powerstrentgh.databinding.FragmentTrainerHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AllRequestUserToTrainnerFragment extends Fragment {

    private FragmentAllRequestUserToTrainnerBinding binding;
    private ReqUserToTrainnerAdapter adapter;
    ArrayList<AddRequserDetailsToDatabase> list;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String reqID;

    public AllRequestUserToTrainnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllRequestUserToTrainnerBinding.inflate(getLayoutInflater(), container, false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            String currentUserId = user.getUid();

            Toast.makeText(requireActivity(), "" + currentUserId, Toast.LENGTH_SHORT).show();
               list = new ArrayList<>();
            adapter = new ReqUserToTrainnerAdapter(list, requireActivity(),currentUserId);
            binding.progressB.setVisibility(View.VISIBLE);
               reference = FirebaseDatabase.getInstance().getReference("TrainnerBooking").child("Info");

               reference.child(currentUserId).addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       list.clear();
                       if (snapshot.exists()) {
                           for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                               MemberBookingDetails model = dataSnapshot.getValue(MemberBookingDetails.class);
                               if (model != null && "Pending".equals(model.getReqstatus())) {
                                   String userId = model.getMemberID();
                                   reqID = model.getReqID();
                                   DatabaseReference trainerRef = FirebaseDatabase.getInstance().getReference("GymUser").child(
                                           "Details").child(userId);
                                   trainerRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                                           if (snapshot.exists()) {
                                               AddRequserDetailsToDatabase userDetail = snapshot.getValue(AddRequserDetailsToDatabase.class);
                                               if (userDetail != null) {
                                                   String username = userDetail.getUsername();
                                                   String userID = userDetail.getUserID();
                                                   String userImage = userDetail.getUserimageurl();
                                                   list.add(new AddRequserDetailsToDatabase(userID,username,userImage,reqID));
                                                   adapter.notifyDataSetChanged();
                                               }
                                           }
                                           binding.progressB.setVisibility(View.GONE);
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError error) {
                                           binding.progressB.setVisibility(View.GONE);
                                           Toast.makeText(requireActivity(), "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                       }
                                   });
                               }
                           }
                       } else {
                           binding.progressB.setVisibility(View.GONE);
                           Toast.makeText(requireActivity(), "No Pending Booking found", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                       binding.progressB.setVisibility(View.GONE);
                       Toast.makeText(requireActivity(), "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });

               binding.requserRecyclerViewID.setAdapter(adapter);
               binding.requserRecyclerViewID.setLayoutManager(new LinearLayoutManager(requireActivity()));
           } else {
               // Handle the case where the user is null
           }
        return binding.getRoot();
    }
}