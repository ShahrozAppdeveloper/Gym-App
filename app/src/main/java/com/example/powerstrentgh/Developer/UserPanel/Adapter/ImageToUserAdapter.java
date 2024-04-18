package com.example.powerstrentgh.Developer.UserPanel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.ExceriseImageModelClass;
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

public class ImageToUserAdapter extends RecyclerView.Adapter<ImageToUserAdapter.TeacherStudentListViewHolder> {
    private ArrayList<ExceriseImageModelClass> mList;
    private Context context;
    DatabaseReference refmember;
    FirebaseDatabase database;
    String currentuserID;


    public ImageToUserAdapter(ArrayList<ExceriseImageModelClass> mList, Context context, String userID) {
        this.mList = mList;
        this.context = context;
        this.currentuserID = userID;
    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_exeriseimage_layout, parent, false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final ExceriseImageModelClass data = mList.get(position);

        Glide.with(context).load(data.getImage())
                .into(holder.Img);

        holder.btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TeacherStudentListViewHolder extends RecyclerView.ViewHolder {
        ImageView Img;

        CardView btnbook;

        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            Img = itemView.findViewById(R.id.imageexID);
            btnbook = itemView.findViewById(R.id.btnbookID);

        }
    }
}
