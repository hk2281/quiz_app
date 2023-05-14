package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;


    private SharedPreferences sharedPrefs;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag", "hi");
                openActivitiTwo();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        sharedPrefs = getSharedPreferences("storage", Context.MODE_PRIVATE);

//        NameDataHolder getName = new NameDataHolder();
        Log.d("fa", sharedPrefs.getString("name",""));
        Log.d("fa",String.valueOf(sharedPrefs.getInt("score", 0)));
    }

    public void openActivitiTwo(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}