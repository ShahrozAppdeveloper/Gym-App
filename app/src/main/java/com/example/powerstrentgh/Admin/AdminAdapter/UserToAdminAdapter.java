package com.example.powerstrentgh.Admin.AdminAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.AdduserDetailsToDatabase;
import com.example.powerstrentgh.R;

import java.util.ArrayList;

public class UserToAdminAdapter extends RecyclerView.Adapter<UserToAdminAdapter.TeacherStudentListViewHolder> {
    private ArrayList<AdduserDetailsToDatabase> mList;
    private Context context;

    public UserToAdminAdapter(ArrayList<AdduserDetailsToDatabase> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_user_layout, parent, false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final AdduserDetailsToDatabase data = mList.get(position);

        Glide.with(context).load(data.getUserimageurl())
                .into(holder.userImg);
        holder.username.setText(data.getUsername());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TeacherStudentListViewHolder extends RecyclerView.ViewHolder {
        ImageView userImg;
        TextView username;

        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            userImg = itemView.findViewById(R.id.userimageID);
            username = itemView.findViewById(R.id.userameText);

        }
    }
}
