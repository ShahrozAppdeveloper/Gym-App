package com.example.powerstrentgh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

 public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
    Context context;
ArrayList<ModelClassPopular>arrayList;


    public AdapterClass(Context context, ArrayList<ModelClassPopular> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.popularcard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.ViewHolder holder, int position) {
holder.image.setImageResource(arrayList.get(position).getImg());
holder.head.setText(arrayList.get(position).getHead());
holder.desc.setText(arrayList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
TextView desc,head;
ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.pbg);
            head=itemView.findViewById(R.id.Heading);

        }
    }
}
