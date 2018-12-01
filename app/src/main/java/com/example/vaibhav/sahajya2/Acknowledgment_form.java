package com.example.vaibhav.sahajya2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Acknowledgment_form extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    Button ackn_btn;
    String food_ack,clothes_ack,blankets_ack,medicines_ack,water_ack,sanitaries_ack;
    CheckBox food,medicines,clothes,blankets,water,sanitaries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgment_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Acknowledgment Form");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ackn_btn = (Button)findViewById(R.id.ack_btn);
        food = (CheckBox)findViewById(R.id.food_box);
        clothes =(CheckBox)findViewById(R.id.clothes_box);
        medicines=(CheckBox)findViewById(R.id.medicine_box);
        blankets =(CheckBox)findViewById(R.id.blankets_box);
        water = (CheckBox)findViewById(R.id.bottled_water_box);
        sanitaries =(CheckBox)findViewById(R.id.sanitary_box);
        firebaseFirestore = FirebaseFirestore.getInstance();

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox)v).isChecked())
                {
                    food_ack ="yes";
                }
                else
                {
                    food_ack ="no";
                }
            }
        });

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox)v).isChecked())
                {
                    clothes_ack = "yes";

                }
                else {
                    clothes_ack ="no";
                }
            }
        });

        medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox)v).isChecked())
                {
                    medicines_ack ="yes";
                }
                else {

                    medicines_ack = "no";
                }
            }
        });

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox)v).isChecked())
                {
                    water_ack = "yes";
                }
                else {

                    water_ack="no";
                }
            }
        });

        sanitaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked())
                {

                    sanitaries_ack ="yes";
                }
                else {
                    sanitaries_ack = "no";
                }
            }
        });

        blankets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox)v).isChecked())
                {
                    blankets_ack = "yes";
                }
                else {
                    blankets_ack = "no";
                }
            }
        });

        ackn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Map<String, String> ack_map = new HashMap<>();
                ack_map.put("Food", food_ack);
                ack_map.put("Clothes", clothes_ack);
                ack_map.put("Medicines",medicines_ack);
                ack_map.put("Sanitaries",sanitaries_ack);
                ack_map.put("Bottled Water",water_ack);
                ack_map.put("Blankets", blankets_ack);
                firebaseFirestore.collection("Acknowledgement").add(ack_map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(Acknowledgment_form.this, "Your response has been submitted", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Acknowledgment_form.this, "Error adding your details to database", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });

            }
        });



    }


}
