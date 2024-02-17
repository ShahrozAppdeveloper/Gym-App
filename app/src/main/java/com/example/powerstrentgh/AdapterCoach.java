package com.example.powerstrentgh;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.ModelCLass.CoachmodelClass;
import com.example.powerstrentgh.User.Fragment.FragmentViewTrainnerProfile;

import java.util.ArrayList;

public class AdapterCoach extends RecyclerView.Adapter<AdapterCoach.ViewHolder> {
    Context context;
    ArrayList<CoachmodelClass> arrayList;

    public AdapterCoach(Context context, ArrayList<CoachmodelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdapterCoach.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usercoachescard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCoach.ViewHolder holder, int position) {
        final CoachmodelClass temp=arrayList.get(position);
        Glide.with(context.getApplicationContext()).load(temp.getImage()).into(holder.coachimg);
        holder.coachnames.setText(arrayList.get(position).getUsername());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Bundle args = new Bundle();
                FragmentViewTrainnerProfile nextFrag= new FragmentViewTrainnerProfile();
                args.putString("uid", temp.getUserid());
                args.putString("imageurl", temp.getImage());
                args.putString("Trainnnername",temp.getUsername());
                nextFrag.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coachimg;
        TextView coachnames;
        Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           coachnames= itemView.findViewById(R.id.userprofilenameid);
           coachimg=itemView.findViewById(R.id.createprofileimageid);
           btn=itemView.findViewById(R.id.btnviewprofileID);
        }
    }
}
