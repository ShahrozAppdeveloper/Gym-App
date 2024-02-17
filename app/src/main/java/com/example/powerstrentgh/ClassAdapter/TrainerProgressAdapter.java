package com.example.powerstrentgh.ClassAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.powerstrentgh.ModelCLass.Trackprogresstrainermodel;
import com.example.powerstrentgh.R;

import java.util.ArrayList;

public class TrainerProgressAdapter extends RecyclerView.Adapter<TrainerProgressAdapter.ViewHolder> {
    ArrayList<Trackprogresstrainermodel> arrayList;
    Context context;

    public TrainerProgressAdapter(ArrayList<Trackprogresstrainermodel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TrainerProgressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trackprogresscard,parent,false);
        return new TrainerProgressAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerProgressAdapter.ViewHolder holder, int position) {
        final Trackprogresstrainermodel temp=arrayList.get(position);
        holder.clientusername.setText(temp.getCilentusername());
        holder.taskname.setText(temp.getTaskTitle());
        holder.tasktype.setText(temp.getTaskType());
        holder.taskday.setText(temp.getTaskDay());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskname,taskday,tasktype,clientusername;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskname=itemView.findViewById(R.id.progresstasknm);
            taskday=itemView.findViewById(R.id.progresstasktime);
            tasktype=itemView.findViewById(R.id.progresstasktype);
            clientusername=itemView.findViewById(R.id.trackusernm);

        }
    }
}
