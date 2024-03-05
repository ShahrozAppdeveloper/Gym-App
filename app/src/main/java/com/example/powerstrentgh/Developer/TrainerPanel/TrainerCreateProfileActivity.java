package com.example.powerstrentgh.Developer.TrainerPanel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.powerstrentgh.R;

public class TrainerCreateProfileActivity extends AppCompatActivity {
    ImageView trainerImg;
    EditText trainerName;
    CardView createProfileButton;
    private AutoCompleteTextView autoCompleteTextView;
    private String selectedItem;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trainer_create_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        trainerImg = findViewById(R.id.trainerCreateProfileImg);
        trainerName = findViewById(R.id.trainercreateprofilenameid);
        createProfileButton = findViewById(R.id.createtrainerprofilebuttonid);

        String[] items = {"Biceps", "Triceps", "Shoulders", "Chest", "Legs", "Wings"};

        // Get AutoCompleteTextView reference
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        // Create ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);

        // Set the adapter to AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) parent.getItemAtPosition(position);

                Toast.makeText(TrainerCreateProfileActivity.this, "Selected Item: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });

        trainerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                imagePickerActivityResult.launch(galleryIntent);
            }
        });
    }

    ActivityResultLauncher<Intent> imagePickerActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        imageUri = result.getData().getData();
                        trainerImg.setImageURI(imageUri);
                    }
                }
            }
    );
}