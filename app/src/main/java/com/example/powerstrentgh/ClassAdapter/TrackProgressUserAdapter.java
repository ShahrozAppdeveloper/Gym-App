package com.example.powerstrentgh.ClassAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.powerstrentgh.ModelCLass.TrackProgressModelClass;
import com.example.powerstrentgh.R;

import java.util.ArrayList;

public class TrackProgressUserAdapter extends RecyclerView.Adapter<TrackProgressUserAdapter.ViewHolder> {
    Context context;
    ArrayList<TrackProgressModelClass> arrayList;

    public TrackProgressUserAdapter(Context context, ArrayList<TrackProgressModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TrackProgressUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.popularcard,parent,false);
        return new TrackProgressUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackProgressUserAdapter.ViewHolder holder, int position) {
        final TrackProgressModelClass temp=arrayList.get(position);
        holder.textView.setText(arrayList.get(position).getImageName());
        Glide.with(context.getApplicationContext()).load(temp.getImageurl()).into(holder.imageView);
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
            imageView=itemView.findViewById(R.id.pbg);
            textView=itemView.findViewById(R.id.Heading);

        }
    }
}
