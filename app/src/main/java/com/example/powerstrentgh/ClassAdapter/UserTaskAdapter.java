package com.example.powerstrentgh.ClassAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.powerstrentgh.ModelCLass.TaskManagerModelClass;
import com.example.powerstrentgh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class UserTaskAdapter extends RecyclerView.Adapter<UserTaskAdapter.ViewHolder> {
    ArrayList<TaskManagerModelClass> arrayList;
    Context context;

    public UserTaskAdapter(ArrayList<TaskManagerModelClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usertaskcard,parent,false);
        return new UserTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTaskAdapter.ViewHolder holder, int position) {
        final TaskManagerModelClass temp=arrayList.get(position);
        holder.taskname.setText(temp.getTaskTitle());
        holder.tasktype.setText(temp.getTaskType());
        holder.taskday.setText(temp.getTaskDay());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Upload(temp.getTaskTitle(), temp.getTaskDay(),temp.getCilentusername(),temp.getTaskType(),temp.getCilentuserID(),temp.getCurrentUserId());
            }
        });
    }
    private void Upload(String taskname,String taskday,String currenusername,String type,String Id,String uid){
        HashMap <String,Object> hashMap=new HashMap<>();
        hashMap.put("TaskTitle",""+taskname);
        hashMap.put("TaskType",""+type);
        hashMap.put("TaskDay",""+taskday);
        hashMap.put("Cilentusername",""+currenusername);
        hashMap.put("Id",""+Id);
        hashMap.put("currentid",""+uid);
        FirebaseDatabase database;
        DatabaseReference ref;
        database=FirebaseDatabase.getInstance();
        ref= database.getReference("Task Completed Details").child("progress");
        ref.child(uid).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(context, "Completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskname,taskday,tasktype;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskname=itemView.findViewById(R.id.tasknm);
            taskday=itemView.findViewById(R.id.tasktime);
            tasktype=itemView.findViewById(R.id.tasktype);
            imageView=itemView.findViewById(R.id.donetsk);
        }
    }
}
