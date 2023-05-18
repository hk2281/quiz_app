package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button score_btn;

    private Button exit_btn;

    private Long vl;


    private SharedPreferences sharedPrefs;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        score_btn = findViewById(R.id.score_btn);
        exit_btn = findViewById(R.id.exit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag", "hi");
                openActivitiTwo();
            }
        });

        score_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openScoreActivity();
            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void openScoreActivity() {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    public void openActivitiTwo(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}