package com.example.powerstrentgh.ClassAdapter;

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
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.Trainner.TrainerChatActivity;

import java.util.ArrayList;

public class TrainerBookedUserAdapter extends RecyclerView.Adapter<TrainerBookedUserAdapter.ViewHolder> {
    Context context;
    ArrayList<BookingTrainnerDetailModelClass> arrayList;

    public TrainerBookedUserAdapter(Context context, ArrayList<BookingTrainnerDetailModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TrainerBookedUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trainerbookedusercard,parent,false);
        return new TrainerBookedUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerBookedUserAdapter.ViewHolder holder, int position) {
        final BookingTrainnerDetailModelClass temp=arrayList.get(position);
        holder.textView.setText(arrayList.get(position).getUsername());
        Glide.with(context.getApplicationContext()).load(temp.getUserimage()).into(holder.imageView);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, TrainerChatActivity.class);
            intent.putExtra("Username",temp.getUsername());
            intent.putExtra("uid",temp.getUserid());
            intent.putExtra("userimage",temp.getUserimage());
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
        Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.contactsImage);
            textView=itemView.findViewById(R.id.contactName);
        }
    }
}
