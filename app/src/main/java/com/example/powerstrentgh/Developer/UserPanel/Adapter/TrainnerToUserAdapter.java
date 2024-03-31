package com.example.powerstrentgh.Developer.UserPanel.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.MemberBookingDetails;
import com.example.powerstrentgh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrainnerToUserAdapter extends RecyclerView.Adapter<TrainnerToUserAdapter.TeacherStudentListViewHolder> {
    private ArrayList<AddTrainnerDetailToDatabase> mList;
    private Context context;
    DatabaseReference refmember;
    FirebaseDatabase database;
    String currentuserID;


    public TrainnerToUserAdapter(ArrayList<AddTrainnerDetailToDatabase> mList, Context context, String userID) {
        this.mList = mList;
        this.context = context;
        this.currentuserID = userID;
    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainner_to_user_layout, parent, false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final AddTrainnerDetailToDatabase data = mList.get(position);

        Glide.with(context).load(data.getTrainnerimageurl())
                .into(holder.trainnerImg);
        holder.trainnername.setText(data.getTrainnername());
//   creating instance
        database = FirebaseDatabase.getInstance();
        holder.btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refmember = database.getReference("MemberBooking");
                String trainneID = data.getTrainnerID();
                String ID = refmember.push().getKey().toString();

                MemberBookingDetails obj = new MemberBookingDetails(currentuserID, trainneID, ID, "Pending");

                refmember.child("Info").child(currentuserID).child(ID).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AddTrainnerBookingDetails(trainneID, ID, obj);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        // check the data is exist or not
        DatabaseReference reftrainner = database.getReference("MemberBooking");
        reftrainner.child("Info").child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        MemberBookingDetails model = dataSnapshot.getValue(MemberBookingDetails.class);
                        if (model != null && model.getTrainnerID().equals(data.getTrainnerID())) {
                            mList.remove(data);
                            notifyDataSetChanged();
                            break; // Break after removal to avoid ConcurrentModificationException
                        }else{
                            Toast.makeText(context, "No Trainner yet", Toast.LENGTH_SHORT).show();

                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void AddTrainnerBookingDetails(String trainneID, String ID, MemberBookingDetails obj) {
        DatabaseReference reftrainner = database.getReference("TrainnerBooking");
        reftrainner.child("Info").child(trainneID).child(ID).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Request Send", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TeacherStudentListViewHolder extends RecyclerView.ViewHolder {
        ImageView trainnerImg;
        TextView trainnername;
        CardView btnbook;

        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            trainnerImg = itemView.findViewById(R.id.trainnerimageID);
            trainnername = itemView.findViewById(R.id.trainnerNameText);
            btnbook = itemView.findViewById(R.id.btnbookID);

        }
    }
}
