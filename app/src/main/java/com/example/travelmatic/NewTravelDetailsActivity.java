package com.example.travelmatic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewTravelDetailsActivity extends AppCompatActivity {
    private EditText place, price, description;
    private FirebaseAuth mAuth;
    private Button myButton;
    private ImageView myImage;

    Toolbar toolbar;

    private final int PERMISSION_CODE = 1001;
    private final int IMAGE_PICK_CODE = 1011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_travel_details);

        mAuth = FirebaseAuth.getInstance();

        myImage = findViewById(R.id.my_image);
        place = findViewById(R.id.Place_txt);
        price = findViewById(R.id.price_txt);
        description = findViewById(R.id.description_txt);

        myButton = findViewById(R.id.button_fab);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED){
                        //permission denied
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup to request runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else{
                    //system OS is < Marshmallow
                    pickImageFromGallery();
                }
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE: {
                if (grantResults.length >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery();
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void pickImageFromGallery() {
        //Intent to pick image
        Intent intent = new  Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    private void setNewDeals() {

        String place_txt = place.getText().toString().trim();
        String price_txt = price.getText().toString().trim();
        String description_txt =description.getText().toString().trim();


    }

    public void myImage(View view) {
        startActivity(new Intent(this,NewTravelDetailsActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            assert data != null;
            myImage.setImageURI(data.getData());
        }
    }
}
