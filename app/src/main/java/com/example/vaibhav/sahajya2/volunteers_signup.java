package com.example.vaibhav.sahajya2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.HashMap;
import java.util.Map;

public class volunteers_signup extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText email;
    private EditText mobile;
    String[] SPINNERLIST = {"Relief Camp 1", "Relief Camp 2", "Relief Camp 3", "Relief Camp 4"};
    private FirebaseFirestore firebaseFirestore;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers_signup);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Volunteer's Portal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();
        name = (EditText) findViewById(R.id.nameId);
        age = (EditText) findViewById(R.id.age);
        gender = (EditText) findViewById(R.id.gender);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        submit = (Button) findViewById(R.id.submit);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        final MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner) findViewById(R.id.choice_camp);
        betterSpinner.setAdapter(arrayAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namedb = name.getText().toString();
                String genderdb = gender.getText().toString();
                String emaildb = email.getText().toString();
                String agedb = age.getText().toString();
                String mobiledb = mobile.getText().toString();
                String camp_pref = betterSpinner.getText().toString();

                Map<String, String> user_signup = new HashMap<>();

                user_signup.put("name", namedb);
                user_signup.put("gender", genderdb);
                user_signup.put("email", emaildb);
                user_signup.put("age", agedb);
                user_signup.put("contact", mobiledb);
                user_signup.put("camp_preference", camp_pref);
                firebaseFirestore.collection("Volunteers").add(user_signup).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(volunteers_signup.this, "Your details have been submitted", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //String error =e.getMessage();
                        Toast.makeText(volunteers_signup.this, "Error adding your details to database", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });
            }
        });
    }



}