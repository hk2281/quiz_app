package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    ArrayList<State> states = new ArrayList<State>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        setInitialData();
        Log.d("fa","Score act on create");
        RecyclerView recyclerView = findViewById(R.id.listInActivityScore);
        StateAdapter adapter = new StateAdapter(this, states);
        recyclerView.setAdapter(adapter);


    }
    private void setInitialData(){

        states.add(new State ("mary", 1, R.drawable.flag));
        states.add(new State ("egor",5, R.drawable.flag));

    }
}