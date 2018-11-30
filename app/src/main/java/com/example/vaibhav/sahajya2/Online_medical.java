package com.example.vaibhav.sahajya2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Online_medical extends AppCompatActivity {

    ImageButton chat_bttn;
    TextView q1;
    TextView a1;
    ImageButton q1_bullet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_medical);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Online Medical Assist");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    }
}
