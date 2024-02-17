package com.example.powerstrentgh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.ModelCLass.AddUserDetailToRealtime;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    ArrayList<AddUserDetailToRealtime> arrayList;

    public UserAdapter(Context context, ArrayList<AddUserDetailToRealtime> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trainerusercard,parent,false);
     return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
       final AddUserDetailToRealtime temp=arrayList.get(position);
        Glide.with(context.getApplicationContext()).load(temp.getImageul()).into(holder.imageView);
        holder.textView.setText(arrayList.get(position).getCurentusername());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.userprofilenameid);
            imageView=itemView.findViewById(R.id.createprofileimageid);
        }
    }
}
