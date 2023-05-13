package com.example.myapplication;

import android.util.Log;

import java.util.List;

public class QuizManager {
    public DatabaseQuizFactory databaseQuizFactory;

    public QuizManager(DatabaseQuizFactory databaseQuizFactory) {

        this.databaseQuizFactory = databaseQuizFactory;
    }

    public List<Quiz> createQuizFromDatabase() {

        return databaseQuizFactory.getQuiz();
    }
}
