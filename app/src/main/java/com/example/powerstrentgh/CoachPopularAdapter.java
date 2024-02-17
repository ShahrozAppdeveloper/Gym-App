package com.example.powerstrentgh;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.ModelCLass.BookingTrainnerDetailModelClass;
import com.example.powerstrentgh.ModelCLass.BookingUserDetail;
import com.example.powerstrentgh.Trainner.TrainerChatActivity;
import com.example.powerstrentgh.User.UserChatActivity;

import java.util.ArrayList;

public class CoachPopularAdapter extends RecyclerView.Adapter<CoachPopularAdapter.ViewHolder> {
Context context;
ArrayList<BookingUserDetail> arrayList;

    public CoachPopularAdapter(Context context, ArrayList<BookingUserDetail> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CoachPopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coaches_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoachPopularAdapter.ViewHolder holder, int position) {
        final BookingUserDetail temp=arrayList.get(position);
        holder.textView.setText(arrayList.get(position).getTrainnername());
        Glide.with(context.getApplicationContext()).load(arrayList.get(position).getTrainnerimage()).into(holder.imageView);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, UserChatActivity.class);
                intent.putExtra("Username",temp.getTrainnername());
                intent.putExtra("uid",temp.getTrainnerID());
                intent.putExtra("userimage",temp.getTrainnerimage());
                context.startActivity(intent);
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
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        imageView=itemView.findViewById(R.id.coachimg);
        textView=itemView.findViewById(R.id.coachname);
        button=itemView.findViewById(R.id.chatuser);
        }
    }
}
