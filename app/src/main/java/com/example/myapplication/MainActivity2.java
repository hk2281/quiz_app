package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    public ArrayList<Quiz> quizzes = new ArrayList<>();

    public List<String> options1 = new ArrayList<>();

    public Button btn_one, btn_2, btn_3,btn_4;
    public TextView tv_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_question = findViewById(R.id.tv_question);

        btn_one = findViewById(R.id.btn_one);
        btn_2 = findViewById(R.id.btn_two);
        btn_3 = findViewById(R.id.btn_three);
        btn_4 = findViewById(R.id.btn_four);

        options1.add("A) Option 1");
        options1.add("B) Option 2");
        options1.add("C) Option 3");
        options1.add("D) Option 4");

        Quiz quiz1 = new Quiz("What is the capital of France?", options1, 1);
        quizzes.add(quiz1);

        btn_one.setText(options1.get(0));

        Log.d("quiz", String.valueOf(quizzes.get(0).getOptions()));
    }
}