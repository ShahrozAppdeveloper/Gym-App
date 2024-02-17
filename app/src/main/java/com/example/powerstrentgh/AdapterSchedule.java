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
import com.example.powerstrentgh.ModelCLass.BookingTrainnerDetailModelClass;
import com.example.powerstrentgh.Trainner.TrainerAddTask;

import java.util.ArrayList;

public class AdapterSchedule extends RecyclerView.Adapter<AdapterSchedule.ViewHolder> {

    Context context;
    ArrayList<BookingTrainnerDetailModelClass> arrayList;

    public AdapterSchedule(@NonNull Context context, ArrayList<BookingTrainnerDetailModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public AdapterSchedule.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.schedulecard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSchedule.ViewHolder holder, int position) {
        final BookingTrainnerDetailModelClass temp=arrayList.get(position);
        holder.textView.setText(arrayList.get(position).getUsername());
        Glide.with(context.getApplicationContext()).load(temp.getUserimage()).into(holder.imageView);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Bundle args = new Bundle();
                TrainerAddTask nextFrag= new TrainerAddTask();
                args.putString("uid", temp.getUserid());
                args.putString("imageurl", temp.getUserimage());
                args.putString("username",temp.getUsername());
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
        ImageView imageView;
        TextView textView;
                Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.createprofileimageid);
            textView=itemView.findViewById(R.id.userprofilenameid);
            btn=itemView.findViewById(R.id.btntaskID);
        }
    }
}
