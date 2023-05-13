package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    public List<Quiz> quizzes;

    public Button btn_one, btn_2, btn_3,btn_4;
    public TextView tv_question;

    private String answer;
    private  int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_question = findViewById(R.id.tv_question);

        btn_one = findViewById(R.id.btn_one);
        btn_2 = findViewById(R.id.btn_two);
        btn_3 = findViewById(R.id.btn_three);
        btn_4 = findViewById(R.id.btn_four);

        //TODO: contain this in new class and call it from here

        DatabaseQuizFactory questionFactory = new DatabaseQuizFactory();
        QuizManager questionManager = new QuizManager(questionFactory);
        quizzes = questionManager.createQuizFromDatabase();
//        this for loop set text for the buttons from the options list

        nextQuestion();

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_one.getText() == answer){
                    Log.d("fa", String.valueOf(btn_one.getText()));
                    Toast.makeText(MainActivity2.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }
                else {
                    Toast.makeText(MainActivity2.this, "You Gay", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_2.getText() == answer){
                    Log.d("fa", String.valueOf(btn_2.getText()));
                    nextQuestion();
                }
                else {
                    Toast.makeText(MainActivity2.this, "You Gay", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_3.getText() == answer){
                    Log.d("fa", String.valueOf(btn_3.getText()));
                    nextQuestion();
                }
                else {
                    Toast.makeText(MainActivity2.this, "You Gay", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_4.getText() == answer){
                    Log.d("fa", String.valueOf(btn_4.getText()));
                    nextQuestion();
                }
                else {
                    Toast.makeText(MainActivity2.this, "You Gay", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void nextQuestion() {

        tv_question.setText(quizzes.get(index).getQuestion());
        btn_one.setText(quizzes.get(index).getOptions().get(0));
        btn_2.setText(quizzes.get(index).getOptions().get(1));
        btn_3.setText(quizzes.get(index).getOptions().get(2));
        btn_4.setText(quizzes.get(index).getOptions().get(3));
        answer = quizzes.get(index).getCorrectAnswer();
        index = index<quizzes.size()-1? index+1: 0;

    }
}