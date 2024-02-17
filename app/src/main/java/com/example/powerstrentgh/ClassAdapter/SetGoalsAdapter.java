package com.example.powerstrentgh.ClassAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.powerstrentgh.ModelCLass.Setgoalmodel;
import com.example.powerstrentgh.R;

import java.util.ArrayList;

public class SetGoalsAdapter extends RecyclerView.Adapter<SetGoalsAdapter.ViewHolder> {
    Context context;
    ArrayList<Setgoalmodel> arrayList;
    private int checkedposition=0;


    public SetGoalsAdapter(Context context, ArrayList<Setgoalmodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    public void SetGoals(ArrayList<Setgoalmodel> arrayList){
        this.arrayList=new ArrayList<>();
        this.arrayList=arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SetGoalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.setgoalscard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetGoalsAdapter.ViewHolder holder, int position) {
holder.bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,desc;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
       name=itemView.findViewById(R.id.namegoal);
       desc=itemView.findViewById(R.id.descgoal);
       img=itemView.findViewById(R.id.selectimg);
        }
        void bind(final Setgoalmodel setgoalmodel){

            if (checkedposition==-1){
img.setVisibility(View.VISIBLE);
            }else {
                if (checkedposition==getAdapterPosition()){
                    img.setVisibility(View.VISIBLE);
                }else {
                    img.setVisibility(View.GONE);
                }
                name.setText(setgoalmodel.getName());
                desc.setText(setgoalmodel.getDesc());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img.setVisibility(View.VISIBLE);
                        if(checkedposition!=getAdapterPosition()){
                            notifyItemChanged(checkedposition);
                            checkedposition=getAdapterPosition();
                        }
                    }
                });
            }


        }
       }
    public Setgoalmodel getSelected(){
        if (checkedposition!=-1){
            return arrayList.get(checkedposition);
        }
        return null;
    }
}
