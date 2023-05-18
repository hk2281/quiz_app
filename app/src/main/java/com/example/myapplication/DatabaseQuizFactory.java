package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQuizFactory implements QuizFactory{

    public DatabaseQuizFactory(){};
//    public List<Quiz> allQuestions = new ArrayList<>();
//    public List<String> options1 = new ArrayList<>();
    public Quiz myQuiz;
    public  List<Quiz> allQuestions = new ArrayList<>();
    @Override
    public List<Quiz> getQuiz() {

        databaseConnection getQuestionCon = new databaseConnection();

        getQuestionCon.getQuestion(new databaseConnection.QuestionCallback() {
            @Override
            public void onGetQuestion(List<Quiz> docSnap) {
                allQuestions = docSnap;
            }
        });

        return allQuestions;
    }
}
