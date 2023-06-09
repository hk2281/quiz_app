package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.health.ServiceHealthStats;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements GetPlayerName.GetPlayerNameListener {

    public List<Quiz> quizzes;

    public Button btn_one, btn_2, btn_3,btn_4;
    public TextView tv_question;

    private String answer;
    private  int index = 0;

    private SharedPreferences context;
    private SharedPreferences sharedPrefs;
    SharedPreferences.Editor ed;

    public static NameDataHolder newNames = new NameDataHolder();
    @Override
    public void onFinishEditDialog(String inputText) {
        Log.d("fa","finisheditdialog foo");

        ed.putString("name", inputText);
        ed.apply();
        Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context = getSharedPreferences("storage",Context.MODE_PRIVATE);
        ed = context.edit();
        showEditDialog();

        tv_question = findViewById(R.id.tv_question);

        btn_one = findViewById(R.id.btn_one);
        btn_2 = findViewById(R.id.btn_two);
        btn_3 = findViewById(R.id.btn_three);
        btn_4 = findViewById(R.id.btn_four);


        databaseConnection getQuestionCon = new databaseConnection();

        getQuestionCon.getQuestion(new databaseConnection.QuestionCallback() {
            @Override
            public void onGetQuestion(List<Quiz> docSnap) {
                quizzes = docSnap;
                nextQuestion(index);
            }
        });

//        DatabaseQuizFactory questionFactory = new DatabaseQuizFactory();
//        QuizManager questionManager = new QuizManager(questionFactory);
//        quizzes = questionManager.createQuizFromDatabase();
//        this for loop set text for the buttons from the options list



        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_one.getText() == answer){
                    Log.d("fa", String.valueOf(btn_one.getText()));
                    Toast.makeText(MainActivity2.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    index = index<=quizzes.size()-1? index+1: 0;
                    nextQuestion(index);
                }
                else {
                    Toast.makeText(MainActivity2.this, "You is lose", Toast.LENGTH_SHORT).show();
                    setCurrentScore(index);
                    returnToMainActivity();
                }
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_2.getText() == answer){
                    Toast.makeText(MainActivity2.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    index = index<=quizzes.size()-1? index+1: 0;
                    nextQuestion(index);
                }
                else {
                    Toast.makeText(MainActivity2.this, "You is lose", Toast.LENGTH_SHORT).show();
                    setCurrentScore(index);
                    returnToMainActivity();
                }
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_3.getText() == answer){
                    Toast.makeText(MainActivity2.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    index = index<=quizzes.size()-1? index+1: 0;
                    nextQuestion(index);
                }
                else {
                    Toast.makeText(MainActivity2.this, "You is lose", Toast.LENGTH_SHORT).show();
                    setCurrentScore(index);
                    returnToMainActivity();
                }
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_4.getText() == answer){
                    Toast.makeText(MainActivity2.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    index = index<=quizzes.size()-1? index+1: 0;
                    nextQuestion(index);
                }
                else {
                    Toast.makeText(MainActivity2.this, "You is lose", Toast.LENGTH_SHORT).show();
                    setCurrentScore(index);
                    returnToMainActivity();
                }
            }
        });
    }
    private void nextQuestion(int question) {

        if(question==quizzes.size()){
            setCurrentScore(question);
            showWinScreenFragment();
        }
        else {
            tv_question.setText(quizzes.get(question).getQuestion());
            btn_one.setText(quizzes.get(question).getOptions().get(0));
            btn_2.setText(quizzes.get(question).getOptions().get(1));
            btn_3.setText(quizzes.get(question).getOptions().get(2));
            btn_4.setText(quizzes.get(question).getOptions().get(3));
            answer = quizzes.get(question).getCorrectAnswer();
        }

    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        GetPlayerName getPlayerNameDialogFragment = GetPlayerName.newInstance("Some Title");
        getPlayerNameDialogFragment.show(fm, "fragment_edit_name");
    }

    private void showWinScreenFragment() {
        FragmentManager fm = getSupportFragmentManager();
        WinScreen alertDialog = WinScreen.newInstance("you Win");
        alertDialog.show(fm, "fragment_alert");
    }

    public void returnToMainActivity(){
        sharedPrefs = getSharedPreferences("storage", Context.MODE_PRIVATE);

//        NameDataHolder getName = new NameDataHolder();
        Log.d("fa", sharedPrefs.getString("name",""));
        Log.d("fa",String.valueOf(sharedPrefs.getInt("score", 0)));
        sendToDbCurrentPlayerSession(
                sharedPrefs.getString("name",""),
                String.valueOf(sharedPrefs.getInt("score", 0)));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void setCurrentScore(int score){
        ed.putInt("score",score);
        ed.apply();
    }

    public void sendToDbCurrentPlayerSession(String playerName, String score){
        databaseConnection forGetCount = new databaseConnection();
        databaseConnection forPostData = new databaseConnection();
        forGetCount.getcollectionCount(new databaseConnection.MyCallback(){
            @Override
            public  void  onComplete(long count){
                Log.d("ff", String.valueOf(count));
                forPostData.postToDb(new Player(playerName,score,count));
            }
            @Override
            public void onGetPlayerScore(ArrayList<State> data){}
        });

    }


}