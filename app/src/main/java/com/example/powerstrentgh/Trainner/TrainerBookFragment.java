package com.example.powerstrentgh.Trainner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.powerstrentgh.ClassAdapter.TrainerBookedUserAdapter;
import com.example.powerstrentgh.ModelCLass.BookingTrainnerDetailModelClass;
import com.example.powerstrentgh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrainerBookFragment extends Fragment {
    TrainerBookedUserAdapter adapterSchedule;
    ArrayList<BookingTrainnerDetailModelClass> list;
    Button addtask;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String uid;

    public TrainerBookFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trainer_book, container, false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();
        trainerscedules(view,uid);
        return view;
    }
    private void trainerscedules(View view,String userid) {

        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.bookedusersrv);
        if (userid != null) {
            list = new ArrayList<>();
            adapterSchedule = new TrainerBookedUserAdapter(getContext(), list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(adapterSchedule);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference(

                    "TrainnerBookingDetail").child(
                    "Trainner");

            ref.child(userid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            BookingTrainnerDetailModelClass obj = dataSnapshot.getValue(BookingTrainnerDetailModelClass.class);
                            list.add(obj);
                        }

                        adapterSchedule.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "not user yet", Toast.LENGTH_SHORT).show();
        }
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