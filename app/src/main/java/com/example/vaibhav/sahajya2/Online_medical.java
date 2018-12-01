package com.example.vaibhav.sahajya2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Online_medical extends AppCompatActivity {

    Button get_loc;

    ImageButton chat_bttn;
    TextView q1,q2,q3,q4,q5,q6,q7;
    TextView a1,a2,a3,a4,a5,a6,a7;
    ImageButton q1_bullet,q2_bullet,q3_bullet,q4_bullet,q5_bullet,q6_bullet,q7_bullet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_medical);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Online Medical Assist");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //gettin users location
       /* get_loc= (Button)findViewById(R.id.get_location);
        get_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Online_medical.this,GetLocation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        */
        chat_bttn = (ImageButton)findViewById(R.id.chat_btn);

        chat_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("shuru","nayi activity invoked");
                Intent intent = new Intent("com.example.vaibhav.askdoc");
                startActivity(intent);
            }
        });
        a1 = (TextView)findViewById(R.id.ans1);
        q1 = (TextView)findViewById(R.id.qus1);
        q1_bullet = (ImageButton)findViewById(R.id.q1_bullet);

        a2 = (TextView)findViewById(R.id.ans2);
        q2 = (TextView)findViewById(R.id.qus2);
        q2_bullet = (ImageButton)findViewById(R.id.q2_bullet);

        a3 = (TextView)findViewById(R.id.ans3);
        q3 = (TextView)findViewById(R.id.qus3);
        q3_bullet = (ImageButton)findViewById(R.id.q3_bullet);

        a4 = (TextView)findViewById(R.id.ans4);
        q4 = (TextView)findViewById(R.id.qus4);
        q4_bullet = (ImageButton)findViewById(R.id.q4_bullet);

        a5 = (TextView)findViewById(R.id.ans5);
        q5 = (TextView)findViewById(R.id.qus5);
        q5_bullet = (ImageButton)findViewById(R.id.q5_bullet);

        a6 = (TextView)findViewById(R.id.ans6);
        q6 = (TextView)findViewById(R.id.qus6);
        q6_bullet = (ImageButton)findViewById(R.id.q6_bullet);

        a7 = (TextView)findViewById(R.id.ans7);
        q7 = (TextView)findViewById(R.id.qus7);
        q7_bullet = (ImageButton)findViewById(R.id.q7_bullet);


        q1_bullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!a1.isShown())
                {
                    a1.setVisibility(View.VISIBLE);
                }
                else
                    a1.setVisibility(View.INVISIBLE);
            }
        });

        q2_bullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!a2.isShown())
                {
                    a2.setVisibility(View.VISIBLE);
                }
                else {
                    a2.setVisibility(View.INVISIBLE);
                }
            }
        });

        q3_bullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!a3.isShown())
                {
                    a3.setVisibility(View.VISIBLE);
                }
                else {
                    a3.setVisibility(View.INVISIBLE);
                }
            }
        });

        q4_bullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!a4.isShown())
                {
                    a4.setVisibility(View.VISIBLE);
                }
                else {
                    a4.setVisibility(View.INVISIBLE);
                }
            }
        });

        q5_bullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!a5.isShown())
                {
                    a5.setVisibility(View.VISIBLE);
                }
                else {
                    a5.setVisibility(View.INVISIBLE);
                }
            }
        });

        q6_bullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!a6.isShown())
                {
                    a6.setVisibility(View.VISIBLE);
                }
                else {
                    a6.setVisibility(View.INVISIBLE);
                }
            }
        });

        q7_bullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!a7.isShown())
                {
                    a7.setVisibility(View.VISIBLE);
                }
                else {
                    a7.setVisibility(View.INVISIBLE);
                }
            }
        });




    }
}
