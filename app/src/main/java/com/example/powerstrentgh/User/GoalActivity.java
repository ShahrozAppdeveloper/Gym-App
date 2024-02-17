package com.example.powerstrentgh.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.powerstrentgh.ClassAdapter.SetGoalsAdapter;
import com.example.powerstrentgh.ModelCLass.Setgoalmodel;
import com.example.powerstrentgh.R;

import java.util.ArrayList;

public class GoalActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Setgoalmodel> setgoalmodels = new ArrayList<>();
    SetGoalsAdapter adapter;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        recyclerView = findViewById(R.id.goalrv);
        button = findViewById(R.id.setgoalnext);

        setgoalmodels.add(new Setgoalmodel("Basic", "New To Gym"));
        setgoalmodels.add(new Setgoalmodel("Inter", "I Know A Little About Gym"));
        setgoalmodels.add(new Setgoalmodel("Advance", "I Am Expert And Have Knowledge"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        adapter = new SetGoalsAdapter(this, setgoalmodels);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelected() != null) {
                    ShowToast(adapter.getSelected().getName());
                    Intent intent=new Intent(getApplicationContext(), UserProfileActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(GoalActivity.this, "No Selection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ShowToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
