package com.example.powerstrentgh.Admin.AdminAdapter;

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
import com.example.powerstrentgh.Developer.TrainerPanel.AddTrainnerDetailToDatabase;
import com.example.powerstrentgh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProveTrainnerAdminAdapter extends RecyclerView.Adapter<ProveTrainnerAdminAdapter.TeacherStudentListViewHolder> {
    private ArrayList<AddTrainnerDetailToDatabase> mList;
    private Context context;



    public ProveTrainnerAdminAdapter(ArrayList<AddTrainnerDetailToDatabase> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_trainner_prove_layout, parent, false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final AddTrainnerDetailToDatabase data = mList.get(position);

        Glide.with(context).load(data.getTrainnerimageurl())
                .into(holder.trainnerImg);
        holder.trainnername.setText(data.getTrainnername());



    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TeacherStudentListViewHolder extends RecyclerView.ViewHolder {
        ImageView trainnerImg;
        TextView trainnername;

        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            trainnerImg = itemView.findViewById(R.id.trainnerimageID);
            trainnername = itemView.findViewById(R.id.trainnerNameText);


        }
    }
}
