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
import com.example.powerstrentgh.ModelCLass.BookingTrainnerDetailModelClass;
import com.example.powerstrentgh.ModelCLass.TaskManagerModelClass;

import java.util.ArrayList;

public class AdapterAllUser extends RecyclerView.Adapter<AdapterAllUser.ViewHolder> {
    ArrayList<TaskManagerModelClass> arrayList;
    Context context;

    public AdapterAllUser(ArrayList<TaskManagerModelClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterAllUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trainerhomecard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllUser.ViewHolder holder, int position) {
        final TaskManagerModelClass temp=arrayList.get(position);
        Glide.with(context.getApplicationContext()).load(temp.getCilentuserimage()).into(holder.imageView);
holder.textView.setText(temp.getCilentusername());
holder.taskname.setText(temp.getTaskTitle());
holder.tasktype.setText(temp.getTaskType());
holder.taskday.setText(temp.getTaskDay());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView,taskname,taskday,tasktype;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.usernm);
            imageView=itemView.findViewById(R.id.userimg);
        taskname=itemView.findViewById(R.id.tasknm);
        taskday=itemView.findViewById(R.id.tasktime);
        tasktype=itemView.findViewById(R.id.tasktype);
        }
    }
}
