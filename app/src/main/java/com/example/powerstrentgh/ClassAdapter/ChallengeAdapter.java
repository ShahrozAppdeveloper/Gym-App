package com.example.powerstrentgh.ClassAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.powerstrentgh.ModelCLass.TrackProgressModelClass;
import com.example.powerstrentgh.ModelCLass.UserCreateChallengeModelClass;
import com.example.powerstrentgh.R;
import com.example.powerstrentgh.User.ChallengeStartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {
    Context context;
    ArrayList<UserCreateChallengeModelClass> arrayList;

    public ChallengeAdapter(Context context, ArrayList<UserCreateChallengeModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ChallengeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.challengelayout,parent,false);
        return new ChallengeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeAdapter.ViewHolder holder, int position) {
        final UserCreateChallengeModelClass temp=arrayList.get(position);
        holder.imageView.setImageResource(arrayList.get(position).getImg());
        holder.textView.setText(arrayList.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, ChallengeStartActivity.class);
                intent.putExtra("Username",temp.getName());
                intent.putExtra("url",temp.getVediourl());
                context.startActivity(intent);
                AddChallenge(temp.getName(),temp.getImg(),holder);
            }
        });

    }
    private void AddChallenge(String videoname,int Imagevideo,ViewHolder holder){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserId = currentUser.getUid();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Track Progress user").child("Progress");
        Bitmap bitmap = BitmapFactory.decodeResource(holder.itemView.getResources(), Imagevideo);
        String filename = "image_" + System.currentTimeMillis() + ".jpg";
        StorageReference imageRef = storageRef.child("images/" + filename);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {

                String imageUrl = uri.toString();
                TrackProgressModelClass obj =new TrackProgressModelClass(imageUrl,videoname);

                databaseRef.child(currentUserId).push().setValue(obj);

            }).addOnFailureListener(e -> {

            });
        }).addOnFailureListener(e -> {
            // Handle any errors in uploading the image
        });
//        HashMap <String,Object> hashMap=new HashMap<>();
//        hashMap.put("videoname",videoname);
//        hashMap.put("ImageVideo", Imagevideo);
//
//        FirebaseDatabase firebaseDatabase;
//        DatabaseReference ref;
//        firebaseDatabase=FirebaseDatabase.getInstance();
//        ref= firebaseDatabase.getReference("Track Progress user");
//        ref.child(videoname).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//             if (task.isSuccessful()){
//                 Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show();
//             }else{
//
//             }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

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
            imageView=itemView.findViewById(R.id.challengeimg);
            textView=itemView.findViewById(R.id.challengename);
            button=itemView.findViewById(R.id.startchallenge);

        }
    }
}
