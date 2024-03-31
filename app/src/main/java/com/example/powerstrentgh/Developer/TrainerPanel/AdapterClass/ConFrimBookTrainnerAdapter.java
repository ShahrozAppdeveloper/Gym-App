package com.example.powerstrentgh.Developer.TrainerPanel.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.AddRequserDetailsToDatabase;
import com.example.powerstrentgh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ConFrimBookTrainnerAdapter extends RecyclerView.Adapter<ConFrimBookTrainnerAdapter.TeacherStudentListViewHolder> {
    private ArrayList<AddRequserDetailsToDatabase> mList;
    private Context context;
    FirebaseDatabase database;
    DatabaseReference reftrainneraccept;
    DatabaseReference refuseraccept;
    String currentuserID;

    public ConFrimBookTrainnerAdapter(ArrayList<AddRequserDetailsToDatabase> mList, Context context, String currentuserID) {
        this.mList = mList;
        this.context = context;
        this.currentuserID = currentuserID;

    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.req_rv_user_layout, parent, false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final AddRequserDetailsToDatabase data = mList.get(position);

        Glide.with(context).load(data.getUserimageurl())
                .into(holder.userImg);
        holder.username.setText(data.getUsername());


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TeacherStudentListViewHolder extends RecyclerView.ViewHolder {
        ImageView userImg,tickImage;
        TextView username;


        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            userImg = itemView.findViewById(R.id.userimageID);
            username = itemView.findViewById(R.id.userNameText);


        }
    }
}
