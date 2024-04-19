package com.example.powerstrentgh.Developer.UserPanel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.Developer.UserPanel.MemberChatWithTrainnerActivity;
import com.example.powerstrentgh.R;

import java.util.ArrayList;

public class BookedUserToTrainnerAdapter extends RecyclerView.Adapter<BookedUserToTrainnerAdapter.TeacherStudentListViewHolder> {
    private ArrayList<AddTrainnerDetailToDatabase> mList;
    private Context context;

    public BookedUserToTrainnerAdapter(ArrayList<AddTrainnerDetailToDatabase> mList, Context context) {
        this.mList = mList;
        this.context = context;

    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_user_to_trainner_layout, parent, false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final AddTrainnerDetailToDatabase data = mList.get(position);

        Glide.with(context).load(data.getTrainnerimageurl())
                .into(holder.trainnerImg);
        holder.trainnername.setText(data.getTrainnername());

        holder.btnmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MemberChatWithTrainnerActivity.class)
                        .putExtra("trainnername",data.getTrainnername())
                        .putExtra("trainnerImage",data.getTrainnerimageurl())
                        .putExtra("trainnerId",data.getTrainnerID()));
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
        CardView btnmsg;


        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            trainnerImg = itemView.findViewById(R.id.trainnerimageID);
            trainnername = itemView.findViewById(R.id.trainnerNameText);
            btnmsg = itemView.findViewById(R.id.btnmsgID);


        }
    }
}
