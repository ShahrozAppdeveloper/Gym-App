package com.example.powerstrentgh.Developer.TrainerPanel.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.Developer.TrainerPanel.CreateMealForUserActivity;
import com.example.powerstrentgh.Developer.TrainerPanel.TrainnerChatActivity;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.AddRequserDetailsToDatabase;
import com.example.powerstrentgh.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ConFrimUserForMealTrainnerAdapter extends RecyclerView.Adapter<ConFrimUserForMealTrainnerAdapter.TeacherStudentListViewHolder> {
    private ArrayList<AddRequserDetailsToDatabase> mList;
    private Context context;
    FirebaseDatabase database;
    DatabaseReference reftrainneraccept;
    DatabaseReference refuseraccept;
    String currentuserID;

    public ConFrimUserForMealTrainnerAdapter(ArrayList<AddRequserDetailsToDatabase> mList, Context context, String currentuserID) {
        this.mList = mList;
        this.context = context;
        this.currentuserID = currentuserID;

    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_user_to_meal_layout, parent, false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final AddRequserDetailsToDatabase data = mList.get(position);

        Glide.with(context).load(data.getUserimageurl())
                .into(holder.userImg);
        holder.username.setText(data.getUsername());

        holder.btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CreateMealForUserActivity.class)
                        .putExtra("username",data.getUsername())
                        .putExtra("userImage",data.getUserimageurl())
                        .putExtra("userId",data.getUserID()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TeacherStudentListViewHolder extends RecyclerView.ViewHolder {
        ImageView userImg,tickImage;
        TextView username;
        CardView btnbook;


        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            userImg = itemView.findViewById(R.id.userimageID);
            username = itemView.findViewById(R.id.userNameText);
            btnbook = itemView.findViewById(R.id.mealbtnmsgID);


        }
    }
}
